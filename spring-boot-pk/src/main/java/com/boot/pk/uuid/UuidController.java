package com.boot.pk.uuid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * UUID
 * <p>本地生成，不基于数据库</p>
 * <p>主键性能差,过长，没有特殊意义，占用空间大</p>
 * <p>B+ 树索引在写的时候有过多的随机写操作</p>
 * @author lizhifu
 * @date 2020/12/16
 */
@RestController
public class UuidController {
    @GetMapping("/uuid")
    public String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
