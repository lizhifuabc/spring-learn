package com.boot.lombok.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * https://projectlombok.org/features/Data
 * 是@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstructor的组合体
 * @author lizhifu
 * @date 2020/12/29
 */
@Data
public class DataExample {
    @NonNull
    private Long id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private Integer age;

    public static void main(String[] args) {
        //@RequiredArgsConstructor已生效
        DataExample example1 = new DataExample(1L);
        //@Getter @Setter已生效
        example1.setName("test");
        example1.setAge(20);
        //@ToString已生效
        System.out.println(example1);
        DataExample example2 = new DataExample(1L);
        //@EqualsAndHashCode已生效
        System.out.println(example1.equals(example2));
    }
}
