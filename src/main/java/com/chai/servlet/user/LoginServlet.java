package com.chai.servlet.user;

import com.chai.pojo.User;
import com.chai.service.user.UserService;
import com.chai.service.user.UserServiceimpl;
import com.chai.util.Constants;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 自动生成的方法存根

        System.out.println("正在登陆 ============ " );
        System.out.println("。。。" );
        System.out.println("。。" );
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //调用service方法，进行用户匹配
        UserService userService = new UserServiceimpl();
        User  user=userService.login(userCode,userPassword);
        if(null != user){//登录成功
            System.out.println("登录成功 ============ " );
            //放入session
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //页面跳转（frame.jsp）
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            System.out.println("登录失败 ============ " );
            //页面跳转（login.jsp）带出提示信息--转发
            req.setAttribute("error", "用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
