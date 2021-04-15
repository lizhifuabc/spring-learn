package com.boot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * 对象配置：
     @Document：在类级别应用，以指示该类是映射到数据库的候选对象。最重要的属性是：
     indexName：用于存储此实体的索引的名称。它可以包含SpEL模板表达式，例如 "log-#{T(java.time.LocalDate).now().toString()}"
     type：映射类型。如果未设置，则使用小写的类的简单名称。（从版本4.0开始不推荐使用）
     shards：索引的分片数。
     replicas：索引的副本数。
     refreshIntervall：索引的刷新间隔。用于索引创建。默认值为“ 1s”。
     indexStoreType：索引的索引存储类型。用于索引创建。默认值为“ fs”。
     createIndex：标记是否在存储库引导中创建索引。默认值为true。请参见使用相应的映射自动创建索引
     versionType：版本管理的配置。默认值为EXTERNAL。
     @Id：在字段级别应用，以标记用于标识目的的字段。
     @Transient：默认情况下，存储或检索文档时，所有字段都映射到文档，此注释不包括该字段。
     @PersistenceConstructor：标记从数据库实例化对象时要使用的给定构造函数，甚至是受保护的程序包。构造函数参数按名称映射到检索到的Document中的键值。
     @Field：在字段级别应用并定义字段的属性，大多数属性映射到各自的Elasticsearch映射定义（以下列表不完整，请查看注释Javadoc以获得完整参考）：
     name：字段名称，它将在Elasticsearch文档中表示，如果未设置，则使用Java字段名称。
     type：字段类型，可以是Text, Keyword, Long, Integer, Short, Byte, Double, Float, Half_Float, Scaled_Float, Date, Date_Nanos, Boolean, Binary, Integer_Range, Float_Range, Long_Range, Double_Range, Date_Range, Ip_Range, Object, Nested, Ip, TokenCount, Percolator, Flattened, Search_As_You_Type。请参阅Elasticsearch映射类型
     format和日期类型的pattern定义。必须为日期类型定义format
     store：标记是否将原始字段值存储在Elasticsearch中，默认值为false。
     analyzer，searchAnalyzer，normalizer用于指定自定义分析和正规化。
     @GeoPoint：将字段标记为geo_point数据类型。如果字段是GeoPoint类的实例，则可以省略。
 * @author lizhifu
 * @date 2021/4/15
 */
@Document(indexName = "user", shards = 3, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Integer id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String address;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime createTime;
    public User(Integer id) {
        this.id = id;
    }
}
