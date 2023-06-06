package com.mybatis.gen.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Map;
import java.util.List;
import com.mybatis.gen.domain.TableTest;
import com.mybatis.gen.service.TableTestService;
import com.mybatis.gen.mapper.TableTestMapper;

/**
* 测试表
* @author lizhifu
* @date 2021-03-18
*/
@Service
@Slf4j
public class TableTestServiceImpl implements TableTestService {

	@Resource
	private TableTestMapper tableTestMapper;

	/**
	* 新增
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public int insert(TableTest tableTest){
		return tableTestMapper.insert(tableTest);
	}

	/**
	* 根据主键刪除
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public int deleteByPrimaryKey(Long primaryKey){
		return tableTestMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	* 根据主键更新
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public int updateByPrimaryKey(TableTest tableTest){
		return tableTestMapper.updateByPrimaryKey(tableTest);
	}

	/**
	* 根据主键查询
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public TableTest selectByPrimaryKey(Long primaryKey){
		return tableTestMapper.selectByPrimaryKey(primaryKey);
	}

	/**
	* 通用查询
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public List<TableTest> selectList(TableTest tableTest){
		return tableTestMapper.selectList(tableTest);
	}

	/**
	* 根据唯一建[info3]查询
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public TableTest selectByInfo3(String info3){
		return tableTestMapper.selectByInfo3(info3);
	}
	/**
	* 根据唯一建[info4]查询
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public TableTest selectByInfo4(String info4){
		return tableTestMapper.selectByInfo4(info4);
	}
	/**
	* 根据唯一约束[info1]查询
	* @author lizhifu
	* @date 2021/03/18
	**/
	@Override
	public TableTest selectByInfo1(TableTest tableTest) {
		return tableTestMapper.selectByInfo1(tableTest);
	}
}
