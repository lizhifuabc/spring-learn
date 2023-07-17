# elasticsearch

Elasticsearch 是一个分布式的 RESTful 风格的搜索和数据分析引擎。

   * 查询 ： Elasticsearch 允许执行和合并多种类型的搜索 — 结构化、非结构化、地理位置、度量指标 — 搜索方式随心而变。
   * 分析 ： 找到与查询最匹配的十个文档是一回事。但是如果面对的是十亿行日志，又该如何解读呢？Elasticsearch 聚合让您能够从大处着眼，探索数据的趋势和模式。
   * 速度 ： Elasticsearch 很快。真的，真的很快。
   * 可扩展性 ： 可以在笔记本电脑上运行。 也可以在承载了 PB 级数据的成百上千台服务器上运行。
   * 弹性 ： Elasticsearch 运行在一个分布式的环境中，从设计之初就考虑到了这一点。
   * 灵活性 ： 具备多个案例场景。数字、文本、地理位置、结构化、非结构化。所有的数据类型都欢迎。
   * HADOOP & SPARK ： Elasticsearch + Hadoop

Elasticsearch是一个高度可伸缩的开源全文搜索和分析引擎。它允许您快速和接近实时地存储、搜索和分析大量数据。

用例：

   * 使用Elasticsearch来存储整个产品目录和库存，并为它们提供搜索和自动完成建议。
   * 收集日志或事务数据，并希望分析和挖掘这些数据，以查找趋势、统计、汇总或异常。使用loghide (Elasticsearch/ loghide /Kibana堆栈的一部分)来收集、聚合和解析数据，然后让loghide将这些数据输入到Elasticsearch中。可以运行搜索和聚合来挖掘任何信息。
   * 警报平台，允许指定如下规则:“我有兴趣购买特定的电子设备，如果下个月任何供应商的产品价格低于X美元，我希望得到通知”。在这种情况下，你可以抓取供应商的价格，将它们推入到Elasticsearch中，并使用其反向搜索(Percolator)功能来匹配价格走势与客户查询，并最终在找到匹配后将警报推送给客户。
   * 分析/业务智能需求，并希望快速调查、分析、可视化，并对大量数据提出特别问题(想想数百万或数十亿的记录)。在这种情况下，使用Elasticsearch来存储数据，然后使用Kibana (Elasticsearch/ loghide /Kibana堆栈的一部分)来构建自定义仪表板，以可视化对您来说很重要的数据的各个方面。此外，还可以使用Elasticsearch聚合功能对数据执行复杂的业务智能查询。

# 概念

transport ：通过 TCP 方式访问 ES 

rest ：通过 HTTP API 方式访问 ES 

> transport 在未来的计划中，准备废弃。

## 下载安装

下载地址：https://www.elastic.co/cn/downloads/elasticsearch

spring 文档地址：

https://spring.io/projects/spring-data-elasticsearch

[Spring Data Elasticsearch - Reference Documentation](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/)

spring boot demo：https://github.com/spring-projects/spring-data-examples/tree/main/elasticsearch/example

启动：

```sh
lizhifudeMBP:bin lizhifu$ cd /Users/lizhifu/Downloads/cloud/elasticsearch-8.8.2/bin
lizhifudeMBP:bin lizhifu$ ./elasticsearch
```

启动日志(部分)（注意观察，里面会有密码）：

```java
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Elasticsearch security features have been automatically configured!
✅ Authentication is enabled and cluster connections are encrypted.

ℹ️  Password for the elastic user (reset with `bin/elasticsearch-reset-password -u elastic`):
  3_HQsDJxoTka_kswNUFu

ℹ️  HTTP CA certificate SHA-256 fingerprint:
  e0c95de5787889357437ab4324ea88fa5a7fbc8ca76e3e5fa0169abc867521d9

ℹ️  Configure Kibana to use this cluster:
• Run Kibana and click the configuration link in the terminal when Kibana starts.
• Copy the following enrollment token and paste it into Kibana in your browser (valid for the next 30 minutes):
  eyJ2ZXIiOiI4LjguMiIsImFkciI6WyIxOTIuMTY4LjMxLjE1Njo5MjAwIl0sImZnciI6ImUwYzk1ZGU1Nzg3ODg5MzU3NDM3YWI0MzI0ZWE4OGZhNWE3ZmJjOGNhNzZlM2U1ZmEwMTY5YWJjODY3NTIxZDkiLCJrZXkiOiJNd1g2WG9rQm5hYVB1LURPNUNHdzo2MENSN1hYN1NHQ3VWLURHTDFzcEJBIn0=

ℹ️  Configure other nodes to join this cluster:
• On this node:
  ⁃ Create an enrollment token with `bin/elasticsearch-create-enrollment-token -s node`.
  ⁃ Uncomment the transport.host setting at the end of config/elasticsearch.yml.
  ⁃ Restart Elasticsearch.
• On other nodes:
  ⁃ Start Elasticsearch with `bin/elasticsearch --enrollment-token <token>`, using the enrollment token that you generated.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```

访问地址：http://127.0.0.1:9200/ 	 elastic/3_HQsDJxoTka_kswNUFu

> 如果出现无法访问的情况，修改config/elasticsearch.yml文件 xpack.security.http.ssl:enabled: true 改为false后正常访问

```java
{
    "name": "lizhifudeMBP",
    "cluster_name": "elasticsearch",
    "cluster_uuid": "4fVxMGv9TqakRl2KvTXRKA",
    "version": {
        "number": "8.8.2",
        "build_flavor": "default",
        "build_type": "tar",
        "build_hash": "98e1271edf932a480e4262a471281f1ee295ce6b",
        "build_date": "2023-06-26T05:16:16.196344851Z",
        "build_snapshot": false,
        "lucene_version": "9.6.0",
        "minimum_wire_compatibility_version": "7.17.0",
        "minimum_index_compatibility_version": "7.0.0"
    },
    "tagline": "You Know, for Search"
}
```

# 索引相关操作

```java
/**
 * 索引相关操作
 *
 * @author lizhifu
 * @date 2021/4/15
 */
@SpringBootTest
public class IndexApiTest {
    @Resource
    private ElasticsearchRestTemplate template;
    /**
     * 索引相关操作
     */
    @Test
    public void index() {
        IndexOperations indexOperations = template.indexOps(User.class);
        System.out.println("索引是否存在：" + indexOperations.exists());
        System.out.println("索引是否删除：" + indexOperations.delete());
        System.out.println("索引创建结果：" + indexOperations.create());
        System.out.println("映射修改结果：" + indexOperations.putMapping());
    }
}
```

> 映射修改结果需要安装相应版本的分词器

## IK分词器

[medcl/elasticsearch-analysis-ik: The IK Analysis plugin integrates Lucene IK analyzer into elasticsearch, support customized dictionary. (github.com)](https://github.com/medcl/elasticsearch-analysis-ik)

