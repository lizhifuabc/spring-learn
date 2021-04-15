package com.boot.elasticsearch;

import com.boot.elasticsearch.entity.User;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    void test() {
        matchAllQuery();
        stringQuery();
        matchQuery();
        matchPhraseQuery();
        rangeQuery();
        boolQuery();
        pageAndSortQuery();
        hl();
        scroll();
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

    void stringQuery() {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("郑州");
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    void matchAllQuery() {
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    void matchQuery() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("address", "上海市");
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    void matchPhraseQuery() {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("address", "上海市");
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    void rangeQuery() {
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("age").gte(20).lte(25);
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    void boolQuery() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> queryBuilderList = new ArrayList<>();
        queryBuilderList.add(QueryBuilders.matchPhraseQuery("name", "张三"));
        queryBuilderList.add(QueryBuilders.rangeQuery("age").gte(15).lte(20));

        // 逻辑或
        queryBuilder.should().addAll(queryBuilderList);
        // 逻辑与
        // boolQueryBuilder.must().addAll(queryBuilderList);

        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        search(query);
    }

    void pageAndSortQuery() {
        NativeSearchQuery query = new NativeSearchQuery(QueryBuilders.matchAllQuery());
        // 分页
        query.setPageable(PageRequest.of(0, 2));
        // 排序
        query.addSort(Sort.by(Sort.Direction.ASC, "id"));
        search(query);
    }

    void hl() {
        // 高亮属性
        HighlightBuilder.Field hf = new HighlightBuilder.Field("address");
        // 高亮前缀
        hf.preTags("<span>");
        // 高亮后缀
        hf.postTags("</span>");
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                // 排序
                // .withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC))
                // 分页
                // .withPageable(PageRequest.of(0, 2))
                // 应用高亮
                .withHighlightFields(hf)
                // 查询条件， 必须要有条件，否则高亮
                .withQuery(QueryBuilders.matchQuery("address", "江苏")).build();
        SearchHits<User> search = template.search(query, User.class);
        List<SearchHit<User>> searchHits = search.getSearchHits();
        for (SearchHit<User> searchHit : searchHits) {
            System.out.println(searchHit);
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            System.out.println(highlightFields);
        }
    }

    void scroll() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery())
                .withFields("message").withPageable(PageRequest.of(0, 2)).build();

        SearchScrollHits<User> scroll =
                template.searchScrollStart(1000, searchQuery, User.class, template.getIndexCoordinatesFor(User.class));
        String scrollId = null;
        List<SearchHit<User>> searchHits = new ArrayList<>();
        while (scroll.hasSearchHits()) {
            searchHits.addAll(scroll.getSearchHits());
            scrollId = scroll.getScrollId();
            scroll =
                    template.searchScrollContinue(scrollId, 1000, User.class, template.getIndexCoordinatesFor(User.class));
        }
        for (SearchHit<User> searchHit : searchHits) {
            System.out.println(searchHit);
        }
        if (scrollId != null) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(scrollId);
            template.searchScrollClear(strings);
        }
    }
}
