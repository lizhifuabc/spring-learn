package com.boot.mapstruct;

import com.boot.mapstruct.dto.SourceDTO;
import com.boot.mapstruct.entity.SourceEntity;
import com.boot.mapstruct.mapper.SourceTargetMapper;

/**
 * Main
 *
 * @author lizhifu
 * @since 2023/9/6
 */
public class Main {
    public static void main(String[] args) {
        SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setTest("test");
        SourceEntity sourceEntity = SourceTargetMapper.MAPPER.dtoToEntity(sourceDTO);
        System.out.println(sourceEntity.getTesting());
    }
}
