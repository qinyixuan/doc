package cn.qyx.doc.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户
 *
 * @author qinyixuan
 * @date 2021/10/11
 */
@ApiModel("用户")
public class User {
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String id;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;
    /**
     * 车
     */
    @ApiModelProperty("车")
    private Car car;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", car=" + car +
                '}';
    }

    /**
     * 通过用户姓名和用户密码获取用户
     *
     * @param userName 用户姓名
     * @param password 用户密码
     * @return {@link User}
     */
    public static User getByUserNameAndPassword(String userName, String password) {
        // 车
        Car car = Car.getCar();

        // 用户
        User user = new User();
        user.setId("1");
        user.setUserName(userName);
        user.setPassword(password);
        user.setCar(car);

        return user;
    }

    /**
     * 通过用户id获取用户
     *
     * @param id 用户id
     * @return {@link User}
     */
    public static User getById(String id) {
        // 车
        Car car = Car.getCar();

        // 用户
        User user = new User();
        user.setId(id);
        user.setUserName("覃一轩");
        user.setPassword("qyx123");
        user.setCar(car);

        return user;
    }
}
