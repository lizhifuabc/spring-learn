package com.mybatis.gen.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;
import java.util.List;
import com.mybatis.gen.domain.Admin;
import com.mybatis.gen.service.AdminService;
import com.mybatis.gen.mapper.AdminMapper;

/**
* 管理员信息
* @author lizhifu
* @date 2021-03-03
*/
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminMapper adminMapper;

	/**
	* 新增
	* @author lizhifu
	* @date 2021/03/03
	**/
	@Override
	public Admin insert(Admin admin){
		return adminMapper.insert(admin);
	}

	/**
	* 根据主键刪除
	* @author lizhifu
	* @date 2021/03/03
	**/
	@Override
	public int deleteByPrimaryKey(Long primaryKey){
		return adminMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	* 根据主键更新
	* @author lizhifu
	* @date 2021/03/03
	**/
	@Override
	public int updateByPrimaryKey(Admin admin){
		return adminMapper.updateByPrimaryKey(admin);
	}

	/**
	* 根据主键查询
	* @author lizhifu
	* @date 2021/03/03
	**/
	@Override
	public Admin selectByPrimaryKey(Long primaryKey){
		return adminMapper.selectByPrimaryKey(primaryKey);
	}

	/**
	* 通用查询
	* @author lizhifu
	* @date 2021/03/03
	**/
	@Override
	public List<Admin> selectList(Admin admin){
		return adminMapper.selectList(admin);
	}


}
