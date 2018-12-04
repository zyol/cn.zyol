package cn.zyol.basic.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private String resultMessage;
    private Integer code;

    public ServiceException(int code, String resultMessage) {
        this.code = code;
        this.resultMessage = resultMessage;
    }


}
