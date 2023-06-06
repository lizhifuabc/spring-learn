package com.boot.gen.mycode.service.impl;

import java.util.List;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.boot.gen.mycode.mapper.MyCodeMapper;
import com.boot.gen.mycode.domain.MyCode;
import com.boot.gen.mycode.service.MyCodeService;

/**
 * 代码生成测试Service业务层处理
 *
 * @author lizhifu
 * @date 2020-12-08T14:27:51.184
 */
@Service
public class MyCodeServiceImpl implements MyCodeService
{
    @Resource
    private MyCodeMapper myCodeMapper;

    /**
     * 查询代码生成测试
     *
     * @param infoId 代码生成测试ID
     * @return 代码生成测试
     */
    @Override
    public MyCode selectMyCodeById(Long infoId)
    {
        return myCodeMapper.selectMyCodeById(infoId);
    }

    /**
     * 查询代码生成测试列表
     *
     * @param myCode 代码生成测试
     * @return 代码生成测试
     */
    @Override
    public List<MyCode> selectMyCodeList(MyCode myCode)
    {
        return myCodeMapper.selectMyCodeList(myCode);
    }

    /**
     * 新增代码生成测试
     *
     * @param myCode 代码生成测试
     * @return 结果
     */
    @Override
    public int insertMyCode(MyCode myCode)
    {
        return myCodeMapper.insertMyCode(myCode);
    }

    /**
     * 修改代码生成测试
     *
     * @param myCode 代码生成测试
     * @return 结果
     */
    @Override
    public int updateMyCode(MyCode myCode)
    {
        return myCodeMapper.updateMyCode(myCode);
    }

    /**
     * 批量删除代码生成测试
     *
     * @param infoIds 需要删除的代码生成测试ID
     * @return 结果
     */
    @Override
    public int deleteMyCodeByIds(Long[] infoIds)
    {
        return myCodeMapper.deleteMyCodeByIds(infoIds);
    }

    /**
     * 删除代码生成测试信息
     *
     * @param infoId 代码生成测试ID
     * @return 结果
     */
    @Override
    public int deleteMyCodeById(Long infoId)
    {
        return myCodeMapper.deleteMyCodeById(infoId);
    }
}