package com.yzstu.web; /**
 *
 */

import com.alibaba.fastjson.JSON;
import com.yzstu.pojo.Brand;
import com.yzstu.pojo.PageBean;
import com.yzstu.service.iml.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "brandServlet", value = "/brandServlet/*")
public class brandServlet extends BaseServlet {
    private BrandServiceImpl brandServiceImpl = new BrandServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();//通过reder读取一行 是一行
        System.out.println(readLine);
        Brand brand = JSON.parseObject(readLine, Brand.class);//转为brand对象
        brandServiceImpl.add(brand);

        response.getWriter().write("success");
    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandServiceImpl.selectAll();
        //转为json
        String toJSONString = JSON.toJSONString(brands);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(toJSONString);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();//通过reder读取一行 是一行
        //System.out.println(readLine);
        int[] id = JSON.parseObject(readLine, int[].class);//转为数组
        brandServiceImpl.deleteById(id);
        response.getWriter().write("success");
    }

    //查询当前页面的内容和总记录数
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _current = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int current = Integer.parseInt(_current);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandServiceImpl.selectByPage(current, pageSize);
        //转为json
        String toJSONString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(toJSONString);
    }

    //分页条件查询当前页面的内容和总记录数
    public void selectByPageCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _current = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int current = Integer.parseInt(_current);
        int pageSize = Integer.parseInt(_pageSize);

        //获取条件查询的条件  使用的post请求体方法  可以直接用
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();//通过reder读取一行 是一行
        Brand brand = JSON.parseObject(readLine, Brand.class);//

        PageBean<Brand> pageBean = brandServiceImpl.selectByPageCondition(current, pageSize, brand);
        //转为json
        String toJSONString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(toJSONString);
    }
}
