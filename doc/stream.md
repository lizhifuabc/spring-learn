# 遍历

```java
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
```

运行结果：

```java
小米
魅族
华为
=======分隔符=======
StreamEntity(name=小米, age=18)
=======分隔符=======
魅族:魅族
小米:小米
```

# 过滤|toList|toMap

```java
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
```

运行结果：

```java
小米
=======分隔符=======
StreamEntity(name=魅族, age=20)
=======分隔符=======
魅族:魅族
```

# 类型转换

```java
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
```

运行结果：

```java
小米
魅族
```

# 合并joining

```java
    @Test
    public void joining(){
        // List
        List<StreamEntity> streamEntityList =Arrays.asList
                (StreamEntity.builder().name("小米").age(18).build(),StreamEntity.builder().name("魅族").age(20).build());
        String str = streamEntityList.stream().map(StreamEntity::getName).collect(Collectors.joining(","));
        System.out.println(str);
    }
```

运行结果：

```java
小米,魅族
```

# 排序sorted

```java
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
```

运行结果：

```java
升序
StreamEntity(name=小米, age=18, score=20)
StreamEntity(name=魅族, age=20, score=18)
StreamEntity(name=华为, age=20, score=20)
降序
StreamEntity(name=魅族, age=20, score=18)
StreamEntity(name=华为, age=20, score=20)
StreamEntity(name=小米, age=18, score=20)
升序升序
StreamEntity(name=小米, age=18, score=20)
StreamEntity(name=魅族, age=20, score=18)
StreamEntity(name=华为, age=20, score=20)
升序降序
StreamEntity(name=魅族, age=20, score=18)
StreamEntity(name=华为, age=20, score=20)
StreamEntity(name=小米, age=18, score=20)
```

# 查找

```java
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
```

运行结果：

```java
StreamEntity(name=魅族, age=20, score=null)
StreamEntity(name=魅族, age=20, score=null)
```

# 统计 

```java
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
```

```java
平均值：15.0
总个数：2
最大值：20
最小值：10
总和值：30
平均值:15.0
最大值:Optional[20]
最小值:Optional[10]
和:30.0
```

