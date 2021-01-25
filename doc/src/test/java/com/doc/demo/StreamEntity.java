package com.doc.demo;

import lombok.Builder;
import lombok.Data;

/**
 * StreamDemo
 *
 * @author lizhifu
 * @date 2021/1/25
 */
@Data
@Builder
public class StreamEntity {
    private String name;
    private Integer age;
    private Integer score;
}
