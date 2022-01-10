package cn.qyx.doc.bean.enums;

/**
 * Http状态码枚举
 *
 * @author qinyixuan
 * @date 2022/1/10
 */
public enum HttpCodeEnum {

    /**
     * 成功
     */
    OK(200, "OK"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    /**
     * 没有找到
     */
    NOT_FOUND(404, "NOT_FOUND"),
    /**
     * 内部服务器错误
     */
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    HttpCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
