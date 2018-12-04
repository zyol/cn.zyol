package cn.zyol.basic.vo;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author Joe
 */
public class Result implements Serializable {

    /**
     * 结果体
     */
    private Object data;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    private Result() {
        super();
    }

    private Result(Integer code) {
        this.code = code;
    }

    public static Result create(Integer code) {
        return new Result(code);
    }

    public static Result createSuccessResult() {
        return create(ResultCode.SUCCESS);
    }

    public static Result createSuccessResult(Object data, String message) {
        return createSuccessResult().setData(data).setMessage(message);
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
}
