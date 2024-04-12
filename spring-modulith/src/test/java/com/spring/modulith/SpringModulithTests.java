package com.spring.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

/**
 * SpringModulith 规范校验
 *
 * @author lizhifu
 * @since 2024/4/12
 */
public class SpringModulithTests {
    ApplicationModules modules = ApplicationModules.of(SpringModulithApplication.class);

    @Test
    void shouldBeCompliant() {
        // 验证模块是否符合规范
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModuleCanvases()
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
