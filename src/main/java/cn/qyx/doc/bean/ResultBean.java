package cn.qyx.doc.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回实体
 *
 * @author qinyixuan
 * @date 2021/10/13
 */
@ApiModel("返回实体")
public class ResultBean<T> {

    /**
     * code码
     */
    @ApiModelProperty("code码")
    private String code;
    /**
     * 消息
     */
    @ApiModelProperty("消息")
    private String message;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 通过id获取返回实体
     *
     * @param id id
     * @return {@link ResultBean}<{@link User}>
     */
    public static ResultBean<User> getResultBean(String id) {
        // 用户
        User user = User.getById(id);

        // 返回实体
        ResultBean<User> resultBean = new ResultBean<>();
        resultBean.setCode("200");
        resultBean.setMessage("success");
        resultBean.setData(user);

        return resultBean;
    }
}
