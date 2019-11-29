package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private String id;
    private String size;
    private String timeLength;
    private Date publishTime;
    private String audioFile;
    private String albumId;
}
