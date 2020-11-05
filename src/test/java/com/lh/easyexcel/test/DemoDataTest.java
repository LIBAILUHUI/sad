package com.lh.easyexcel.test;

import com.alibaba.excel.EasyExcel;
import com.lh.easyexcel.entity.DemoData;
import com.lh.easyexcel.listener.NoModelDataListener;
import com.lh.easyexcel.mapper.DemoDataMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Administrator
 * @description TODO
 * @date 2020/11/2
 */
@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoDataTest {

    @Autowired
    private DemoDataMapper mapper;

    @Value("${linux.demo.path}")
    private String savePath;

    /**
     * 简单导出excel，文件名称为模板名称+日期
     */
    @Test
    public void test(){

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
    }


    @Test
    public void testSpecialExport(){

        String fileName = "";
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int year = instance.get(Calendar.YEAR);
        String month = instance.get(Calendar.MONTH)+1>10?instance.get(Calendar.MONTH)+1+"":"0"+instance.get(Calendar.MONTH)+1;
        String day = instance.get(Calendar.DAY_OF_MONTH)<10?"0"+instance.get(Calendar.DAY_OF_MONTH):instance.get(Calendar.DAY_OF_MONTH)+"";

        fileName = "E:/demoExec/demo指定包含列模板名称"+year+month+day+".xlsx";


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
        //Set<String> excludeColumnFieldNames = new HashSet<String>();
        //excludeColumnFieldNames.add("password");
        //EasyExcel.write(fileName,DemoData.class).excludeColumnFiledNames(excludeColumnFieldNames).sheet("demo排除指定列").doWrite(mapper.findAll());

        Set<String> includeColumnFieldNames = new HashSet<String>();
        includeColumnFieldNames.add("id");
        includeColumnFieldNames.add("loginName");
        EasyExcel.write(fileName,DemoData.class).includeColumnFiledNames(includeColumnFieldNames).sheet("demo指定列").doWrite(mapper.findAll());
    }


    /**
     * 不创建对象的读
     */
    @Test
    public void noModelRead() {
        String fileName = "E:/demoExec/exportDemo.xlsx";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(fileName, new NoModelDataListener()).sheet().doRead();
    }

}
