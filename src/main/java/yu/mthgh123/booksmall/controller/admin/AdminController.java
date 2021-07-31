package yu.mthgh123.booksmall.controller.admin;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yu.mthgh123.booksmall.common.ServiceResultEnum;
import yu.mthgh123.booksmall.entity.AdminUser;
import yu.mthgh123.booksmall.service.AdminUserService;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import yu.mthgh123.booksmall.util.Result;
import yu.mthgh123.booksmall.util.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;

    @GetMapping({"/login"})
    public String login() {
        return "admin/login";
    }

    @GetMapping({"/test"})
    public String test() {
        return "admin/test";
    }


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            //登录成功之后，并不是跳转到后台管理系统首页，而是重定向到/admin/index对应的控制器方法，由该方法先设置path，之后再跳转到/admin/index页面
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登陆失败，请联系作者获得测试账号");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public Result modifyPassword(@RequestParam("originalPassword") String originalPassword, //originalPassword和newPassword都会经过前端检验，检验成功后才会发送给后端
                                 @RequestParam("newPassword") String newPassword,
                                 HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            ResultGenerator.genFailResult(ServiceResultEnum.DATA_IS_NULL.getResult());
        }
        if (adminUser.getLoginPassword().equals(originalPassword)) {
            //因为密码在前端就已经经过了格式是否合格判断，所以当原密码输入正确的情况下，直接进行修改
            String result = adminUserService.updatePassword(loginUserId, newPassword);
            if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
                return ResultGenerator.genSuccessResult();
            }
        } else {
            //前端提示：原密码不正确
            return ResultGenerator.genFailResult(ServiceResultEnum.PASSWORD_ERROR.getResult());
        }
        return ResultGenerator.genFailResult(ServiceResultEnum.JUST_FAILED.getResult());
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}

