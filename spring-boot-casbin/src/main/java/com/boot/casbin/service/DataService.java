package com.boot.casbin.service;

import com.boot.casbin.model.Data;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@Service
public class DataService {
    private String state;

    public DataService() {
        this.state = "DEFAULT";
    }

    public void setState(String state) {
        this.state = state;
    }

    public Data getSecuredData(String source) {
        return new Data(source, "Some Data", System.currentTimeMillis(), state);
    }
}
