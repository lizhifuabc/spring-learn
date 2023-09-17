package com.spring.modulith.order;

import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

/**
 * 订单ID
 *
 * @author lizhifu
 * @since 2023/9/16
 */
public record OrderIdentifier(UUID id) implements Identifier {
}
