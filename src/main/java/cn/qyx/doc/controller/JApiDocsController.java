package cn.qyx.doc.controller;

import cn.qyx.doc.bean.ResultBean;
import cn.qyx.doc.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * JApiDocs控制器
 *
 * @author qinyixuan
 * @date 2021/10/14
 */
@RestController
@RequestMapping("/japidocs")
public class JApiDocsController {

    /**
     * 用户登录接口
     *
     * @param id       用户id
     * @param userName 用户姓名
     * @param password 用户密码
     * @return {@link User}
     */
    @PostMapping("/login")
    public User login(String id, @RequestParam(required = true) String userName, @RequestParam(required = true) String password) {
        return User.getByUserNameAndPassword(userName, password);
    }

    /**
     * 获取用户信息接口
     *
     * @param id 用户id
     * @return {@link User}
     */
    @GetMapping("/get")
    public ResultBean<User> get(@RequestParam(required = true) String id) {
        return ResultBean.getResultBean(id);
    }
}
