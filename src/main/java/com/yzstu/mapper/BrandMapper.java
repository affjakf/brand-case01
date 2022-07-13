package com.yzstu.mapper;

import com.yzstu.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /* // id 主键
        private Integer id;
        // 品牌名称
        private String brandName;
        // 企业名称
        private String companyName;
        // 排序字段
        private Integer ordered;
        // 描述信息
        private String description;
        // 状态：0：禁用  1：启用
        private Integer status;*/
    @Insert("insert  into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    @ResultMap("brandResultMap")
    void add(Brand brand);

    void deleteById(@Param("ids") int[] ids);

    //查询所有记录数

    int selectCountByCondition(Brand brand);

    //查询当前页面的内容
    List<Brand> selectRowsAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand); //查询所有记录数

    @Select("select count(*) from tb_brand")
    int selectCount();

    //查询当前页面的内容
    @Select("select * form tb_brand limit #{begin},#{size}}")
    @ResultMap("brandResultMap")
    List<Brand> selectRows(@Param("begin") int begin, @Param("size") int size);
}
