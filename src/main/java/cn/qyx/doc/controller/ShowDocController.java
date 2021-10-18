package cn.qyx.doc.controller;

import cn.qyx.doc.bean.ResultBean;
import cn.qyx.doc.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ShowDoc控制器
 *
 * @author qinyixuan
 * @date 2021/10/11
 */
@RestController
@RequestMapping("/showdoc")
public class ShowDocController {

    /**
     * showdoc
     * @catalog 用户管理
     * @title 用户登陆
     * @description 用户登录接口
     * @method POST
     * @url {{url}}/runapi/login
     * @param id 否 string 用户id
     * @param userName 是 string 用户姓名
     * @param password 是 string 用户密码
     * @return {"id":"1","userName":"qinyixuan","password":"123456","car":{"name":"玛莎拉蒂","price":"100万元"}}
     * @return_param id string 用户id
     * @return_param userName string 用户姓名
     * @return_param password string 用户密码
     * @return_param car string 车
     * @return_param └─name string 名字
     * @return_param └─price string 价格
     * @remark 更多返回错误代码请看首页的错误代码描述
     * @number 1
     */
    @PostMapping("/login")
    public User login(String id, String userName, String password) {
        return User.getByUserNameAndPassword(userName, password);
    }

    /**
     * showdoc
     * @catalog 用户管理
     * @title 获取用户信息
     * @description 获取用户信息接口
     * @method GET
     * @url {{url}}/runapi/get
     * @param id 是 string 用户id
     * @return {"code":"200","message":"success","data":{"id":"9","userName":"覃一轩","password":"qyx","car":{"name":"玛莎拉蒂","price":"100万元"}}}
     * @return_param code string code码
     * @return_param message string 消息
     * @return_param data string 数据
     * @return_param └─id string 用户id
     * @return_param └─userName string 用户姓名
     * @return_param └─password string 用户密码
     * @return_param └─car string 车
     * @return_param &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;└─name string 名字
     * @return_param &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;└─price string 价格
     * @remark 更多返回错误代码请看首页的错误代码描述
     * @number 2
     */
    @GetMapping("/get")
    public ResultBean<User> get(String id) {
        return ResultBean.getResultBean(id);
    }
}
