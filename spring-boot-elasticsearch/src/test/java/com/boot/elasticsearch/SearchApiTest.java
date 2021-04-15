package com.boot.elasticsearch;

import com.boot.elasticsearch.entity.User;
import org.elasticsearch.index.query.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import javax.annotation.Resource;
import java.util.List;

/**
 * SearchApiTest
 *
 * @author lizhifu
 * @date 2021/4/15
 */
@SpringBootTest
public class SearchApiTest {
    @Resource
    private ElasticsearchRestTemplate template;
    @Test
    void matchPhraseQuery() {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("address", "北京");
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }
    @Test
    void matchQuery() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("address", "北京");
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    @Test
    void matchAllQuery() {
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }
    @Test
    public void stringQuery() {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("北京");
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }
    /**
     * 执行查询
     */
    private void search(Query query) {
        SearchHits<User> search = template.search(query, User.class);
        List<SearchHit<User>> searchHits = search.getSearchHits();
        for (SearchHit<User> searchHit : searchHits) {
            System.out.println(searchHit);
        }
        System.out.println("---------------");
    }
}
