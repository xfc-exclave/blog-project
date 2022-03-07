package cc.chinmoku.springcloudcommon.common;

import lombok.Data;

@Data
public class ResponseObject<T> {

    private String code;

    private String message;

    private T data;

    public ResponseObject(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseObject(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
