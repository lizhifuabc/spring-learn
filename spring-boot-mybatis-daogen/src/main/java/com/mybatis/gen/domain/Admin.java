package com.mybatis.gen.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
/**
* [管理员信息]
* @author lizhifu
* @date 2021-03-02
*/
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 
    */
    private Long userid;
    /**
    * 
    */
    private String mobile;
    /**
    * 
    */
    private String password;
    /**
    * 
    */
    private String name;
    /**
    * 
    */
    private String email;
}