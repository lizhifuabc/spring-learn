package com.boot.gen.mycode.service;

import java.util.List;
import com.boot.gen.mycode.domain.MyCode;

/**
 * 代码生成测试Service接口
 *
 * @author lizhifu
 * @date 2020-12-08T14:22:45.680
 */
public interface MyCodeService
{
    /**
     * 查询代码生成测试
     *
     * @param infoId 代码生成测试ID
     * @return 代码生成测试
     */
    public MyCode selectMyCodeById(Long infoId);

    /**
     * 查询代码生成测试列表
     *
     * @param myCode 代码生成测试
     * @return 代码生成测试集合
     */
    public List<MyCode> selectMyCodeList(MyCode myCode);

    /**
     * 新增代码生成测试
     *
     * @param myCode 代码生成测试
     * @return 结果
     */
    public int insertMyCode(MyCode myCode);

    /**
     * 修改代码生成测试
     *
     * @param myCode 代码生成测试
     * @return 结果
     */
    public int updateMyCode(MyCode myCode);

    /**
     * 批量删除代码生成测试
     *
     * @param infoIds 需要删除的代码生成测试ID
     * @return 结果
     */
    public int deleteMyCodeByIds(Long[] infoIds);

    /**
     * 删除代码生成测试信息
     *
     * @param infoId 代码生成测试ID
     * @return 结果
     */
    public int deleteMyCodeById(Long infoId);
}