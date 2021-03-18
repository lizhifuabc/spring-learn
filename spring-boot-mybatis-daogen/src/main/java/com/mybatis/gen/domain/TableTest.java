package com.mybatis.gen.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
/**
* [测试表]
* @author lizhifu
* @date 2021-03-18
*/
@Data
@ApiModel(value = "测试表", description = "测试表")
public class TableTest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @ApiModelProperty(value = "ID", required = false)
    private Long id;
    /**
    * info1
    */
    @ApiModelProperty(value = "info1", required = false)
    private String info1;
    /**
    * info2
    */
    @ApiModelProperty(value = "info2", required = false)
    private String info2;
    /**
    * info3
    */
    @ApiModelProperty(value = "info3", required = false)
    private String info3;
    /**
    * info4
    */
    @ApiModelProperty(value = "info4", required = false)
    private String info4;
}