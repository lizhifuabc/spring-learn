package com.boot.lombok.example;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * https://projectlombok.org/features/log
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@Log
public class LogExample {

@Slf4j
public class LogExampleOther {

}

@CommonsLog(topic="CounterLog")
private class LogExampleCategory {

}
}
