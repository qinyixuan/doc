package cn.qyx.doc.controller;

import cn.qyx.doc.bean.ResultBean;
import cn.qyx.doc.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Swagger控制器
 *
 * @author qinyixuan
 * @date 2021/10/11
 */
@Api(tags = "用户管理")
@RequestMapping("/swagger")
@RestController
public class SwaggerController {
    /**
     * 登录接口
     *
     * @param id       用户id
     * @param userName 用户姓名
     * @param password 用户密码
     * @return {@link User}
     */
    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", dataType = "string"),
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "string")
    })
    @PostMapping("/login")
    public User login(String id, String userName, String password) {
        return User.getByUserNameAndPassword(userName, password);
    }

    /**
     * 获取用户信息接口
     *
     * @param id 用户id
     * @return {@link User}
     */
    @ApiOperation("获取用户信息接口")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string")
    @GetMapping("/get")
    public ResultBean<User> get(String id) {
        return ResultBean.getResultBean(id);
    }
}
