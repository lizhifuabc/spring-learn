# elasticsearch
# 概念

transport ：通过 TCP 方式访问 ES 

rest ：通过 HTTP API 方式访问 ES 

> transport 在未来的计划中，准备废弃。

## 下载安装

下载地址：https://www.elastic.co/cn/downloads/elasticsearch

spring 文档地址：https://spring.io/projects/spring-data-elasticsearch

​							   https://docs.spring.io/spring-data/elasticsearch/docs/4.1.7/reference/html/#elasticsearch.clients

spring boot demo：https://github.com/spring-projects/spring-data-examples/tree/master/elasticsearch/example

启动：

```sh
lizhifudeMBP:bin lizhifu$ cd /Users/lizhifu/Downloads/cloud/elasticsearch-7.12.0/bin
lizhifudeMBP:bin lizhifu$ ./elasticsearch
```

启动日志(部分)：

```java
2021-04-14T16:22:36,538][INFO ][o.e.n.Node               ] [lizhifudeMBP.mshome.net] version[7.12.0], pid[95001], build[default/tar/78722783c38caa25a70982b5b042074cde5d3b3a/2021-03-18T06:17:15.410153305Z], OS[Mac OS X/10.16/x86_64], JVM[AdoptOpenJDK/OpenJDK 64-Bit Server VM/15.0.1/15.0.1+9]
[2021-04-14T16:22:36,541][INFO ][o.e.n.Node               ] [lizhifudeMBP.mshome.net] JVM home [/Users/lizhifu/Downloads/cloud/elasticsearch-7.12.0/jdk.app/Contents/Home], using bundled JDK [true]
[2021-04-14T16:22:36,542][INFO ][o.e.n.Node               ] [lizhifudeMBP.mshome.net] JVM arguments [-Xshare:auto, -Des.networkaddress.cache.ttl=60, -Des.networkaddress.cache.negative.ttl=10, -XX:+AlwaysPreTouch, -Xss1m, -Djava.awt.headless=true, -Dfile.encoding=UTF-8, -Djna.nosys=true, -XX:-OmitStackTraceInFastThrow, -XX:+ShowCodeDetailsInExceptionMessages, -Dio.netty.noUnsafe=true, -Dio.netty.noKeySetOptimization=true, -Dio.netty.recycler.maxCapacityPerThread=0, -Dio.netty.allocator.numDirectArenas=0, -Dlog4j.shutdownHookEnabled=false, -Dlog4j2.disable.jmx=true, -Djava.locale.providers=SPI,COMPAT, --add-opens=java.base/java.io=ALL-UNNAMED, -XX:+UseG1GC, -Djava.io.tmpdir=/var/folders/2c/wz__rj9n65b6k2npxb_3p_c80000gn/T/elasticsearch-2932438158372358079, -XX:+HeapDumpOnOutOfMemoryError, -XX:HeapDumpPath=data, -XX:ErrorFile=logs/hs_err_pid%p.log, -Xlog:gc*,gc+age=trace,safepoint:file=logs/gc.log:utctime,pid,tags:filecount=32,filesize=64m, -Xms8192m, -Xmx8192m, -XX:MaxDirectMemorySize=4294967296, -XX:InitiatingHeapOccupancyPercent=30, -XX:G1ReservePercent=25, -Des.path.home=/Users/lizhifu/Downloads/cloud/elasticsearch-7.12.0, -Des.path.conf=/Users/lizhifu/Downloads/cloud/elasticsearch-7.12.0/config, -Des.distribution.flavor=default, -Des.distribution.type=tar, -Des.bundled_jdk=true]
2021-04-14T16:22:46,079][INFO ][o.e.n.Node               ] [lizhifudeMBP.mshome.net] starting ...
[2021-04-14T16:22:46,164][INFO ][o.e.x.s.c.PersistentCache] [lizhifudeMBP.mshome.net] persistent cache index loaded
[2021-04-14T16:22:46,293][INFO ][o.e.t.TransportService   ] [lizhifudeMBP.mshome.net] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}
[2021-04-14T16:22:46,567][WARN ][o.e.b.BootstrapChecks    ] [lizhifudeMBP.mshome.net] the default discovery settings are unsuitable for production use; at least one of [discovery.seed_hosts, discovery.seed_providers, cluster.initial_master_nodes] must be configured
[2021-04-14T16:22:46,575][INFO ][o.e.c.c.ClusterBootstrapService] [lizhifudeMBP.mshome.net] no discovery configuration found, will perform best-effort cluster bootstrapping after [3s] unless existing master is discovered
[2021-04-14T16:22:49,583][INFO ][o.e.c.c.Coordinator      ] [lizhifudeMBP.mshome.net] setting initial configuration to VotingConfiguration{zEPjtE5zStKsMJlZBzQ1nA}
[2021-04-14T16:22:49,915][INFO ][o.e.c.s.MasterService    ] [lizhifudeMBP.mshome.net] elected-as-master ([1] nodes joined)[{lizhifudeMBP.mshome.net}{zEPjtE5zStKsMJlZBzQ1nA}{n3VqH1OtQO-rS9xayCt3zA}{127.0.0.1}{127.0.0.1:9300}{cdfhilmrstw}{ml.machine_memory=17179869184, xpack.installed=true, transform.node=true, ml.max_open_jobs=20, ml.max_jvm_size=8589934592} elect leader, _BECOME_MASTER_TASK_, _FINISH_ELECTION_], term: 1, version: 1, delta: master node changed {previous [], current [{lizhifudeMBP.mshome.net}{zEPjtE5zStKsMJlZBzQ1nA}{n3VqH1OtQO-rS9xayCt3zA}{127.0.0.1}{127.0.0.1:9300}{cdfhilmrstw}{ml.machine_memory=17179869184, xpack.installed=true, transform.node=true, ml.max_open_jobs=20, ml.max_jvm_size=8589934592}]}
[2021-04-14T16:22:50,010][INFO ][o.e.c.c.CoordinationState] [lizhifudeMBP.mshome.net] cluster UUID set to [J42UKPDlQ-29sObul3Yw5A]
[2021-04-14T16:22:50,117][INFO ][o.e.c.s.ClusterApplierService] [lizhifudeMBP.mshome.net] master node changed {previous [], current [{lizhifudeMBP.mshome.net}{zEPjtE5zStKsMJlZBzQ1nA}{n3VqH1OtQO-rS9xayCt3zA}{127.0.0.1}{127.0.0.1:9300}{cdfhilmrstw}{ml.machine_memory=17179869184, xpack.installed=true, transform.node=true, ml.max_open_jobs=20, ml.max_jvm_size=8589934592}]}, term: 1, version: 1, reason: Publication{term=1, version=1}
[2021-04-14T16:22:50,162][WARN ][o.e.c.r.a.DiskThresholdMonitor] [lizhifudeMBP.mshome.net] high disk watermark [90%] exceeded on [zEPjtE5zStKsMJlZBzQ1nA][lizhifudeMBP.mshome.net][/Users/lizhifu/Downloads/cloud/elasticsearch-7.12.0/data/nodes/0] free: 12.3gb[5.2%], shards will be relocated away from this node; currently relocating away shards totalling [0] bytes; the node is expected to continue to exceed the high disk watermark when these relocations are complete
[2021-04-14T16:22:50,167][INFO ][o.e.h.AbstractHttpServerTransport] [lizhifudeMBP.mshome.net] publish_address {127.0.0.1:9200}, bound_addresses {[::1]:9200}, {127.0.0.1:9200}
[2021-04-14T16:22:50,186][INFO ][o.e.n.Node               ] [lizhifudeMBP.mshome.net] started
```

访问地址：http://127.0.0.1:9200/

```java
{
  "name" : "lizhifudeMBP.mshome.net",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "J42UKPDlQ-29sObul3Yw5A",
  "version" : {
    "number" : "7.12.0",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "78722783c38caa25a70982b5b042074cde5d3b3a",
    "build_date" : "2021-03-18T06:17:15.410153305Z",
    "build_snapshot" : false,
    "lucene_version" : "8.8.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

