package com.spring.boot.resilience4j.exception;

import java.util.function.Predicate;

/**
 * RecordFailurePredicate:记录失败的异常
 *
 * @author lizhifu
 * @since 2023/9/11
 */
public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        return !(throwable instanceof BusinessException);
    }
}
