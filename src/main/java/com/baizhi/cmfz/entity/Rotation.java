package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "轮播图")
public class Rotation {

    @Excel(name = "轮播图主键", width = 50, needMerge = true)
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "描述")
    private String describel;
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 12, needMerge = true)
    private Date createTime;
    @Excel(name = "状态")
    private String status;
    @ExcelIgnore
    private String photo;

}
