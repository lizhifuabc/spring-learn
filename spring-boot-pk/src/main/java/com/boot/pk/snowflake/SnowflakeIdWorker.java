package com.boot.pk.snowflake;

/**
 * TwitterSnowflake
 * 1 bit：统一都是 0,正数
 * 41 bits：表示的是时间戳，单位是毫秒
 * 10 bits：记录工作机器 id，5 个 bits 代表机房 id，5 个 bits 代表机器 id
 * 12 bits：这个是用来记录同一个毫秒内产生的不同 id
 * @author lizhifu
 * @date 2020/12/16
 */
public class SnowflakeIdWorker {
    /**工作ID **/
    private long workerId;
    /**数据中心ID**/
    private long datacenterId;
    /** 机器id所占的位数 */
    private final long workerIdBits = 5L;
    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 5L;
    /** 这个是二进制运算，就是 5 bit最多只能有31个数字，支持的最大机器id，结果是31 **/
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /** 这个是一个意思，就是 5 bit最多只能有31个数字，结果是31 **/
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1608104479898L;
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;
    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;
    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;
    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /** 毫秒内序列(0~4095) */
    private long sequence;

    /**
     * 构造
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long datacenterId){
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return 下一个ID
     */
    public synchronized long nextId() {
        //获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //if条件里表示当前调用和上一次调用落在了相同毫秒内，只能通过第三部分，序列号自增来判断为唯一，所以+1.
        if (lastTimestamp == timestamp) {
            // 这个意思是说一个毫秒内最多只能有4096个数字
            // 无论你传递多少进来，这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            //同一毫秒的序列数已经达到最大，只能等待下一个毫秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //不同毫秒内，序列号置为0
            //执行到这个分支的前提是currTimestamp > lastTimestamp，说明本次调用跟上次调用对比，已经不再同一个毫秒内了，这个时候序号可以重新回置0了。
            sequence = 0;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        // 这儿就是将时间戳左移，放到 41 bit那儿；
        // 将机房 id左移放到 5 bit那儿；
        // 将机器id左移放到5 bit那儿；将序号放最后12 bit；
        // 最后拼接起来成一个 64 bit的二进制数字，转换成 10 进制就是个 long 型
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }
    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
}
