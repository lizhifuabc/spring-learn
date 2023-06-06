package com.mybatis.gen.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import com.mybatis.gen.service.TableTestService;
import com.mybatis.gen.domain.TableTest;
import com.mybatis.gen.base.BaseController;
import com.mybatis.gen.base.R;

    import io.swagger.annotations.*;

/**
* 测试表
* @author lizhifu
* @date 2021-03-18
*/
@Api(tags = "测试表")
@RestController
@RequestMapping(value = "/tableTest")
@Slf4j
public class TableTestController extends BaseController{

    @Resource
    private TableTestService tableTestService;

    /**
    * 新增
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "新增",httpMethod ="POST")
    @RequestMapping("/insert")
    public R insert(TableTest tableTest){
        return new R().success(tableTestService.insert(tableTest));
    }

    /**
    * 根据主键刪除
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "根据主键刪除",httpMethod ="GET")
    @RequestMapping("/deleteByPrimaryKey")
    public R deleteByPrimaryKey(Long primaryKey){
        return new R().success(tableTestService.deleteByPrimaryKey(primaryKey));
    }

    /**
    * 根据主键更新
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "根据主键更新",httpMethod ="POST")
    @RequestMapping("/updateByPrimaryKey")
    public R updateByPrimaryKey(TableTest tableTest){
        return new R().success(tableTestService.updateByPrimaryKey(tableTest));
    }

    /**
    * 根据主键查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "根据主键查询",httpMethod ="GET")
    @RequestMapping("/selectByPrimaryKey")
    public R<TableTest> selectByPrimaryKey(Long primaryKey){
        return new R().success(tableTestService.selectByPrimaryKey(primaryKey));
    }

    /**
    * 通用查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "通用查询",httpMethod ="POST")
    @RequestMapping("/selectList")
    public R<TableTest> selectList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                        @RequestParam(required = false, defaultValue = "10") int pageSize,
                        TableTest tableTest) {
        PageHelper.startPage(pageNum, pageSize, false);
        return new R().success(tableTestService.selectList(tableTest));
    }
    /**
    * 根据唯一建[info3]查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "根据唯一建[info3]查询",httpMethod ="GET")
    @RequestMapping("/selectByInfo3")
    public R<TableTest> selectByInfo3(String info3){
        return new R().success(tableTestService.selectByInfo3(info3));
    }
    /**
    * 根据唯一建[info4]查询
    * @author lizhifu
    * @date 2021/03/18
    **/
    @ApiOperation(value = "根据唯一建[info4]查询",httpMethod ="GET")
    @RequestMapping("/selectByInfo4")
    public R<TableTest> selectByInfo4(String info4){
        return new R().success(tableTestService.selectByInfo4(info4));
    }
}
