package cn.willian.coding.redis.utils;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 07:38:59
 */
@Data
public class Response<T> {

    private int code;

    private String message;

    private T data;

    public static <T> Response<T> ok(T data) {

        Response<T> response = new Response<>();
        response.setData(data);
        response.setCode(200);
        return response;
    }

    public static <T> Response<T> ok() {

        return ok(null);
    }

    public static <T> Response<T> fail(String message) {

        Response<T> response = new Response<>();
        response.setCode(400);
        response.setMessage(message);
        return response;
    }
}
