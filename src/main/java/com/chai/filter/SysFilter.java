package com.chai.filter;

import com.chai.pojo.User;
import com.chai.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest  request=(HttpServletRequest)servletRequest;
        HttpServletResponse  response=(HttpServletResponse)servletResponse;
        User user=(User)request.getSession().getAttribute(Constants.USER_SESSION);
        if(user == null){
            response.sendRedirect("/smbms/error.jsp");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
