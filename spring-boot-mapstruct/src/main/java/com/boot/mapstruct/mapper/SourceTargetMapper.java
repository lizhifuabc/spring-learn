package com.boot.mapstruct.mapper;

import com.boot.mapstruct.dto.SourceDTO;
import com.boot.mapstruct.entities.SourceEntity;
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

    /**
     * 转换
     * @param s SourceDTO 对象
     * @return SourceEntity 对象
     */
    @Mapping( source = "test", target = "testing" )
    SourceEntity dtoToEntity(SourceDTO s );
}
