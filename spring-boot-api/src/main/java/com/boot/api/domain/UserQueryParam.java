package com.boot.api.domain;

import com.boot.api.annotation.Amount;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * UserQueryParam
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@Data
public class UserQueryParam {
    @NotNull
    private String name;
    @NotNull
    @Min(18)
    @Max(100)
    private Integer age;
    @Amount
    private BigDecimal amount;
}
