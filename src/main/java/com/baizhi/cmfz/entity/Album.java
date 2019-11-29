package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private String id;
    private String title;
    private Integer score;
    private String author;
    private String announcer;
    private Integer chapterNumber;
    private String albumIntroduction;
    private String status;
    private Date publishTime;
    private Date uploadTime;
    private String illustration;
}
