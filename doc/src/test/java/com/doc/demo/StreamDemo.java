package com.doc.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StreamDemo
 *
 * @author lizhifu
 * @date 2021/1/25
 */
public class StreamDemo {
    private List<StreamEntity> list = new ArrayList<>();
//    @BeforeAll
    public static void init(){
        System.out.println("数据初始化");
        // 创建集合
        List<String> list = Arrays.asList("张三", "李四", "王五");
        // 创建一个串行流
        Stream<String> stream = list.stream();
        System.out.println(stream);
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
        System.out.println(parallelStream);
    }
    @Test
    public void forEach(){
        // 基本类型
        List<String> list = Arrays.asList("小米", "魅族", "华为");
        list.stream().forEach(result->{
            System.out.println(result);
        });
        System.out.println("=======分隔符=======");
        // List
        List<StreamEntity> streamEntityList = new ArrayList<>();
        StreamEntity streamEntity = StreamEntity.builder().name("小米").age(18).build();
        streamEntityList.add(streamEntity);
        streamEntityList.stream().forEach(result->{
            System.out.println(result);
        });
        System.out.println("=======分隔符=======");
        //map
        Map<String,String> map = new HashMap<>();
        map.put("小米","小米");
        map.put("魅族","魅族");
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+":"+entry.getValue());
        });
    }
    @Test
    public void filter(){
        // 基本类型
        List<String> list = Arrays.asList("小米", "魅族", "华为");
        list = list.stream().filter(result-> result.equals("小米")).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("=======分隔符=======");
        // List
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(18).build(),StreamEntity.builder().name("魅族").age(20).build());
        streamEntityList = streamEntityList.stream().filter(result->result.getAge() == 20).collect(Collectors.toList());
        streamEntityList.forEach(streamEntity1 -> {
            System.out.println(streamEntity1.toString());
        });
        System.out.println("=======分隔符=======");
        //map
        Map<String,String> map = new HashMap<>();
        map.put("小米","小米");
        map.put("魅族","魅族");
        map = map.entrySet().stream().filter(result->result.getKey().equals("魅族")).collect(Collectors.toMap(result->result.getKey(),result->result.getValue()));
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+":"+entry.getValue());
        });
    }
    @Test
    public void match(){
        // List
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(18).build(),StreamEntity.builder().name("魅族").age(20).build());
        List<String> list = streamEntityList.stream().map(StreamEntity::getName).collect(Collectors.toList());
        list.forEach(result -> {
            System.out.println(result);
        });
    }
    @Test
    public void joining(){
        // List
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(18).build(),StreamEntity.builder().name("魅族").age(20).build());
        String str = streamEntityList.stream().map(StreamEntity::getName).collect(Collectors.joining(","));
        System.out.println(str);
    }
    @Test
    public void sorted(){
        // List
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(18).score(20).build()
                        ,StreamEntity.builder().name("魅族").age(20).score(18).build()
                        , StreamEntity.builder().name("华为").age(20).score(20).build()
                );
        System.out.println("升序");
        List<StreamEntity> streamEntities = streamEntityList.stream().sorted(Comparator.comparing(StreamEntity::getAge)).collect(Collectors.toList());
        streamEntities.forEach(streamEntity -> System.out.println(streamEntity.toString()));
        System.out.println("降序");
        streamEntities = streamEntityList.stream().sorted(Comparator.comparing(StreamEntity::getAge).reversed()).collect(Collectors.toList());
        streamEntities.forEach(streamEntity -> System.out.println(streamEntity.toString()));
        System.out.println("升序升序");
        streamEntities = streamEntityList.stream().sorted(Comparator.comparing(StreamEntity::getAge).thenComparing(StreamEntity::getScore)).collect(Collectors.toList());
        streamEntities.forEach(streamEntity -> System.out.println(streamEntity.toString()));
        System.out.println("升序降序");
        streamEntities = streamEntityList.stream().sorted((s1,s2)->{
            if (s1.getScore() != s2.getScore()) {
                return s1.getScore() - s2.getScore();
            } else {
                return s2.getAge() - s1.getAge();
            }
        }).collect(Collectors.toList());
        streamEntities.forEach(streamEntity -> System.out.println(streamEntity.toString()));
    }
    @Test
    public void find(){
        // List 串行 匹配第一个
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(18).build(),StreamEntity.builder().name("魅族").age(20).build());
        Optional<StreamEntity> streamEntity = streamEntityList.stream().filter(result-> result.getAge() == 20).findFirst();
        streamEntity.ifPresent(System.out::println);
        // List 并行 匹配任意一个
        Optional<StreamEntity> streamEntity1 = streamEntityList.stream().filter(result-> result.getAge() == 20).findAny();
        streamEntity1.ifPresent(System.out::println);
    }
    @Test
    public void sum(){
        // List 统计
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(10).build(),StreamEntity.builder().name("魅族").age(20).build());
        IntSummaryStatistics intSummaryStatistics =
                streamEntityList.stream().mapToInt(StreamEntity::getAge).summaryStatistics();
        System.out.println("平均值：" + intSummaryStatistics.getAverage());
        System.out.println("总个数：" + intSummaryStatistics.getCount());
        System.out.println("最大值：" + intSummaryStatistics.getMax());
        System.out.println("最小值：" + intSummaryStatistics.getMin());
        System.out.println("总和值：" + intSummaryStatistics.getSum());
        // 单独
        // 平均值
        Double aDouble = streamEntityList.stream().collect(Collectors.averagingDouble(StreamEntity::getAge));
        System.out.println("平均值:"+aDouble);
        // 最大值和最小值
        Optional<Integer> max = streamEntityList.stream().map(StreamEntity::getAge).max(Integer::compare);
        Optional<Integer> min = streamEntityList.stream().map(StreamEntity::getAge).min(Integer::compare);
        System.out.println("最大值:"+max);
        System.out.println("最小值:"+min);
        // 求和
        Double sumScore = streamEntityList.stream().mapToDouble(StreamEntity::getAge).sum();
        System.out.println("和:"+sumScore);
    }
}
