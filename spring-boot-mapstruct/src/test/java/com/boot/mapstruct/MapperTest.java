package com.boot.mapstruct;

import com.boot.mapstruct.dto.SourceDTO;
import com.boot.mapstruct.entities.SourceEntity;
import com.boot.mapstruct.mapper.SourceTargetMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 执行Main
 *
 * @author lizhifu
 * @since 2023/6/1
 */
@SpringBootTest
public class MapperTest {
    @Test
    public void mapper() {
        SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setTest("test");
        SourceEntity sourceEntity = SourceTargetMapper.MAPPER.dtoToEntity(sourceDTO);
        System.out.println(sourceEntity.getTesting());
    }
}
