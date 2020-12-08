package com.boot.gen.mycode.domain;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 代码生成测试对象 my_code
 *
 * @author lizhifu
 * @date 2020-12-08T14:01:35.399
 */
@Data
public class MyCode{
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long infoId;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 编码
     */
    private String code;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}