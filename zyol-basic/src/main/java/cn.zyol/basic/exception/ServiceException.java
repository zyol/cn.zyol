package cn.zyol.basic.exception;

import cn.zyol.basic.vo.ResultCode;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private String resultMessage;
    private Integer code;

    public ServiceException(int code, String resultMessage) {
        this.code = code;
        this.resultMessage = resultMessage;
    }

    public ServiceException(String message) {
        super(message);
        this.code = ResultCode.SERVICE_ERROR;
    }
}
