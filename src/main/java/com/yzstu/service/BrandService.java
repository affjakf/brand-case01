package com.yzstu.service;

import com.yzstu.pojo.Brand;
import com.yzstu.pojo.PageBean;

import java.util.List;

/**
 *
 */
public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteById(int [] ids);
    PageBean<Brand> selectByPage(int currentPage, int PageSize);
    PageBean<Brand> selectByPageCondition(int currentPage, int PageSize,Brand brand);
}
