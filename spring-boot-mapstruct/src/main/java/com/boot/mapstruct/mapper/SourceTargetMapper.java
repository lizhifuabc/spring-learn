package com.boot.mapstruct.mapper;

import com.boot.mapstruct.dto.SourceDTO;
import com.boot.mapstruct.entities.TargetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 类型转换
 *
 * @author lizhifu
 * @since 2023/6/1
 */
@Mapper
public interface SourceTargetMapper {
    SourceTargetMapper MAPPER = Mappers.getMapper( SourceTargetMapper.class );

    @Mapping( source = "test", target = "testing" )
    TargetEntity toTarget(SourceDTO s );
}
