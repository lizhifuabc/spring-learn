package com.boot.elasticsearch.base;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * 基础类
 *
 * @author lizhifu
 * @date 2021/4/14
 */
@Slf4j
public abstract class BaseElasticsearchService {
    @Resource
    protected RestHighLevelClient highLevelClient;
    /**
     * create elasticsearch index (asyc)
     *
     * @param index elasticsearch index
     * @author fxbin
     */
    protected void createIndexRequest(String index) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(index);
            // Settings for this index
            request.settings(Settings.builder().put("index.number_of_shards", elasticsearchProperties.getIndex().getNumberOfShards()).put("index.number_of_replicas", elasticsearchProperties.getIndex().getNumberOfReplicas()));

            CreateIndexResponse createIndexResponse = client.indices().create(request, COMMON_OPTIONS);

            log.info(" whether all of the nodes have acknowledged the request : {}", createIndexResponse.isAcknowledged());
            log.info(" Indicates whether the requisite number of shard copies were started for each shard in the index before timing out :{}", createIndexResponse.isShardsAcknowledged());
        } catch (IOException e) {
            throw new RuntimeException("创建索引 {" + index + "} 失败");
        }
    }
    /**
     * create elasticsearch index (asyc)
     *
     * @param index elasticsearch index`
     * @author fxbin
     */
    protected void createIndexRequest(String index) {
        IndexRequest request = new IndexRequest("spring-data")
                .id(UUID.randomUUID().toString())
                .source(Collections.singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(IMMEDIATE);
        try {
            IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
