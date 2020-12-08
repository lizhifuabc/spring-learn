package com.boot.gen;

import com.boot.gen.domain.GenTable;
import com.boot.gen.domain.GenTableColumn;
import com.boot.gen.mapper.GenTableColumnMapper;
import com.boot.gen.service.GenService;
import com.boot.gen.utils.GenUtils;
import com.boot.gen.utils.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.StringWriter;

/**
 * GenService 测试
 *
 * @author lizhifu
 * @date 2020/12/8
 */
@SpringBootTest
public class GenServiceTest {
    @Resource
    GenService genService;
    @Resource
    GenTableColumnMapper genTableColumnMapper;
    @Test
    public void getPkColumn(){
        GenTableColumn genTableColumn = GenUtils.getPkColumn(genTableColumnMapper.selectDbTableColumnsByName("my_info"));
        System.out.println(genTableColumn.toString());
    }
    @Test
    public void init(){
        GenTable genTable = genService.initGenTable("my_code");
        genService.initGenTableColumn(genTable);
        System.out.println(genTable.toString());
    }
    @Test
    public void codeGen(){
        VelocityInitializer.initVelocity();
        // 渲染模板
        StringWriter sw = new StringWriter();
        GenTable genTable = genService.initGenTable("my_code");
        genService.initGenTableColumn(genTable);
        VelocityContext velocityContext = genService.prepareContext(genTable);
        Template tpl = Velocity.getTemplate("vm/mapper.xml.vm","utf-8");
        tpl.merge(velocityContext, sw);
        System.out.println(sw.toString());
    }
}
