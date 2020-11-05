package com.lh.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @description TODO
 * @date 2020/11/2
 */
@Data
@TableName("importexcel")
public class DemoData implements Serializable {


    private static final long serialVersionUID = 8760579087610239747L;

    @TableId("id")
    @ColumnWidth(50)
    @ExcelProperty("编号")
    private String id;

    @TableField("version")
    @ExcelProperty("版本")
    private String version;

    @TableField("userName")
    @ExcelProperty("用户名")
    private String userName;

    @TableField("loginName")
    @ExcelProperty("登录名")
    private String loginName;

    @TableField("gender")
    @ExcelProperty("性别")
    private Integer gender;

    @TableField("bumen")
    @ExcelProperty("部门")
    private String bumen;

    @TableField("tel")
    @ExcelProperty("联系方式")
    private String tel;

    @TableField("password")
    //@ExcelIgnore
    @ExcelProperty("密码")
    private String password;

    @TableField("createTime")
    @ExcelProperty("创建时间")
    private Date createTime;

    @TableField("url")
    @ExcelProperty("路径")
    private String url;

    @TableField("pic")
    @ExcelProperty("图片")
    private String pic;


}
