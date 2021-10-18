package cn.qyx.doc.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 车
 *
 * @author qinyixuan
 * @date 2021/10/13
 */
@ApiModel("车")
public class Car {

    /**
     * 名字
     */
    @ApiModelProperty("名字")
    private String name;
    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    /**
     * 获取车
     *
     * @return {@link Car}
     */
    public static Car getCar() {
        Car car = new Car();
        car.setName("玛莎拉蒂");
        car.setPrice("100万元");
        return car;
    }
}
