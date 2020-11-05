package com.lh.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.lh.easyexcel.entity.DemoData;
import com.lh.easyexcel.mapper.DemoDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @description TODO
 * @date 2020/11/3
 */
@PropertySource("classpath:export.properties")
@RestController
@CrossOrigin
public class DemoController {


    @Autowired
    private DemoDataMapper mapper;

    @Value("${linux.demo.path}")
    private String savePath;

    /**
     * 简单导出excel，文件名称为模板名称+日期
     */
    @RequestMapping("export")
    public String export(){

        List<DemoData> demoList = mapper.findAll();


        String fileName = "";
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int year = instance.get(Calendar.YEAR);
        String month = instance.get(Calendar.MONTH)+1>10?instance.get(Calendar.MONTH)+1+"":"0"+instance.get(Calendar.MONTH)+1;
        String day = instance.get(Calendar.DAY_OF_MONTH)<10?"0"+instance.get(Calendar.DAY_OF_MONTH):instance.get(Calendar.DAY_OF_MONTH)+"";





        //fileName = "E:/demoExec/demo模板名称"+year+month+day+".xlsx";
        fileName = savePath+year+month+day+".xlsx";
        File file1 = new File(fileName);
        String substring = fileName.substring(0, fileName.lastIndexOf("/"));
        System.out.println(substring);
        File file2 = new File(substring);
        if(!file2.exists()){
            file2.mkdir();
            if(!file1.exists()){
                try {
                    file1.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        EasyExcel.write(file1,DemoData.class).sheet("demo").doWrite(demoList);
        return "success";

    }



}
