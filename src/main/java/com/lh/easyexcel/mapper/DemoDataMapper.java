package com.lh.easyexcel.mapper;

import com.lh.easyexcel.entity.DemoData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoDataMapper {


    @Select("select * from importexcel")
    List<DemoData> findAll();
}
