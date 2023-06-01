package com.boot.mapstruct.mapper;

import com.boot.mapstruct.dto.SourceDTO;
import com.boot.mapstruct.entities.TargetEntity;

/**
 * 执行Main
 *
 * @author lizhifu
 * @since 2023/6/1
 */
public class Main {
    public static void main( String[] args ) {
        SourceDTO s = new SourceDTO();
        s.setTest( "5" );

        TargetEntity t = SourceTargetMapper.MAPPER.toTarget( s );
        System.out.println( t.getTesting() );
    }
}
