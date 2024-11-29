package cn.willian.coding.redis.limiter.strategy;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 11:50:39
 */
public abstract class AbstractRedisRateLimiter implements RedisRateLimiter, InitializingBean, BeanPostProcessor {

    @Resource
    protected RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 注册到工厂
        RedisRateLimiterFactory.register(getLimitWay(), this);
        return bean;
    }

    protected long getTimeStamp(Long period, TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECONDS:
                return period;
            case MINUTES:
                return period * 60;
            case HOURS:
                return period * 60 * 60;
            case DAYS:
                return period * 24 * 60 * 60;
            default:
                throw new IllegalArgumentException("timeUnit:" + timeUnit + " not support");
        }
    }
}
