package com.tenco.blog_test.model;


import com.tenco.blog_test.utils.MyDateUtil;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
@Data
@Table(name = "board_tb")
@Entity
public class Board {

    @Id // 기본키
    // IDENTITY 전략 : 데이터베이스의 기본 전략을 사용한다. -> Auto_Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }

}
