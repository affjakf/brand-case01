package com.yzstu.web; /**
 *
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();
        //截取方法名
        int i = uri.lastIndexOf("/");
        String method = uri.substring(i + 1);
        //通过请求路径获取到相应的方法
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method clsMethod = cls.getMethod(method, HttpServletRequest.class, HttpServletResponse.class );
            clsMethod.invoke(this,req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
