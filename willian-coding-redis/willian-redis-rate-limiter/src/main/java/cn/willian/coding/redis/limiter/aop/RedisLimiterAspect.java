package cn.willian.coding.redis.limiter.aop;

import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.willian.coding.redis.limiter.annotation.Limiter;
import cn.willian.coding.redis.limiter.strategy.RedisRateLimiter;
import cn.willian.coding.redis.limiter.strategy.RedisRateLimiterFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 22:34:00
 */
@Slf4j
@Aspect
@Component
public class RedisLimiterAspect {

    // 切入点
    @Pointcut(value = "@annotation(cn.willian.coding.redis.limiter.annotation.Limiter)")
    public void redisLimiterPointcut() {}

    // 环绕通知
    @Around("redisLimiterPointcut()")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取方法签名
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取方法上的注解
        Limiter limiter = method.getAnnotation(Limiter.class);
        if (Objects.isNull(limiter)) {
            return joinPoint.proceed();
        }
        // 根据不同配置选择不同的配置KEY
        String key = this.getKey(limiter, method);
        // 选择对应的限流器
        RedisRateLimiter redisRateLimiter = RedisRateLimiterFactory.getRedisRateLimiter(limiter.limitWay());
        // 判断是否超出请求范围
        if (redisRateLimiter.tryAcquire(key, limiter)) {
            return joinPoint.proceed();
        }
        // 若超出范围，异常提醒
        throw new RuntimeException("请求太频繁，请稍后再试");
    }

    private String getKey(Limiter limiter, Method method) {

        String key;
        switch (limiter.limitType()) {
            case IP:
                key = this.getIpAddress();
                break;
            case CUSTOMER:
                key = limiter.key();
                break;
            default:
                key = method.getDeclaringClass().getName() + "-" + method.getName();
                break;
        }
        return key;
    }

    private String getIpAddress() {
        String ipAddress;
        try {
            ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return "";
            }
            HttpServletRequest request = requestAttributes.getRequest();
            ipAddress = request.getHeader("x-forwarded-for");
            if (StringUtils.hasLength(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.hasLength(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.hasLength(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
