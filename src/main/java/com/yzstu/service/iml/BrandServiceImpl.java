package com.yzstu.service.iml;

import com.yzstu.mapper.BrandMapper;
import com.yzstu.pojo.Brand;
import com.yzstu.pojo.PageBean;
import com.yzstu.service.BrandService;
import com.yzstu.util.SqlSessionFactoryUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 *
 */
public class BrandServiceImpl implements BrandService {
    //获取sqlsession
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandmapper.selectAll();
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        brandmapper.add(brand);
        sqlSession.close();

    }

    @Override
    public void deleteById(int[] id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        brandmapper.deleteById(id);
        sqlSession.close();
    }

    public PageBean<Brand> selectByPage(int currentPage, int PageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * 5;
        int size = PageSize;
        //返回当前页面的内容
        List<Brand> brandList = mapper.selectRows(begin, size);
        //System.out.println(brandList);
        //查询总记录数
        int selectCount = mapper.selectCount();
        //System.out.println(selectCount);
        PageBean<Brand> pageBean = new PageBean<>();
        //封装
        pageBean.setRows(brandList);
        pageBean.setTotalCount(selectCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageCondition(int currentPage, int PageSize,Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * 5;
        int size = PageSize;
        //返回当前页面的内容,条件
        List<Brand> brandList = mapper.selectRowsAndCondition(begin, size,brand);
        //System.out.println(brandList);
        //查询总记录数
        int selectCount = mapper.selectCountByCondition(brand);
        //System.out.println(selectCount);
        PageBean<Brand> pageBean = new PageBean<>();
        //封装
        pageBean.setRows(brandList);
        pageBean.setTotalCount(selectCount);
        sqlSession.close();
        return pageBean;
    }
}
