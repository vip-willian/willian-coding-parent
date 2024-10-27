package cn.willian.coding.redis.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import cn.willian.coding.redis.utils.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 08:20:18
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常拦截
     */
    @ExceptionHandler({RuntimeException.class, Exception.class, Throwable.class})
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> allExceptionHandler(NativeWebRequest request, Throwable ex) {
        log.error(ex.getMessage(), ex);
        return Response.fail("系统出现异常，请稍微再试");
    }
}
