package com.mybatis.gen.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import com.mybatis.gen.service.AdminService;
import com.mybatis.gen.domain.Admin;
import com.mybatis.gen.base.BaseController;
import com.mybatis.gen.base.R;

/**
* 管理员信息
* @author lizhifu
* @date 2021-03-02
*/
@RestController
@RequestMapping(value = "/admin")
@Slf4j
public class AdminController extends BaseController{

    @Resource
    private AdminService adminService;

    /**
    * 新增
    * @author lizhifu
    * @date 2021/03/02
    **/
    @RequestMapping("/insert")
    public R insert(Admin admin){
        return new R().success(adminService.insert(admin));
    }

    /**
    * 根据主键刪除
    * @author lizhifu
    * @date 2021/03/02
    **/
    @RequestMapping("/deleteByPrimaryKey")
    public R deleteByPrimaryKey(Long primaryKey){
        return new R().success(adminService.deleteByPrimaryKey(primaryKey));
    }

    /**
    * 根据主键更新
    * @author lizhifu
    * @date 2021/03/02
    **/
    @RequestMapping("/updateByPrimaryKey")
    public R updateByPrimaryKey(Admin admin){
        return new R().success(adminService.updateByPrimaryKey(admin));
    }

    /**
    * 根据主键查询
    * @author lizhifu
    * @date 2021/03/02
    **/
    @RequestMapping("/selectByPrimaryKey")
    public R selectByPrimaryKey(Long primaryKey){
        return new R().success(adminService.selectByPrimaryKey(primaryKey));
    }

    /**
    * 通用查询
    * @author lizhifu
    * @date 2021/03/02
    **/
    @RequestMapping("/selectList")
    public R selectList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                        @RequestParam(required = false, defaultValue = "10") int pageSize,
                        Admin admin) {
        PageHelper.startPage(pageNum, pageSize, false);
        return new R().success(adminService.selectList(admin));
    }
}
