package com.boot.api.domain;

import com.boot.api.annotation.Amount;
import jakarta.validation.constraints.*;
import lombok.Data;

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
