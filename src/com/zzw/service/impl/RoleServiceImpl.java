package com.zzw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzw.mapper.RoleMapper;
import com.zzw.pojo.Role;
import com.zzw.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	RoleMapper roleMapper;
	@Override
	public List<Role> findAllRole() {
		return roleMapper.findAllRole();
	}

}
