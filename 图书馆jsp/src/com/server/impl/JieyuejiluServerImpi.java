package com.server.impl;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.JieyuejiluMapper;
import com.entity.Jieyuejilu;
import com.server.JieyuejiluServer;
@Service
public class JieyuejiluServerImpi implements JieyuejiluServer {
   @Resource
   private JieyuejiluMapper gdao;
	@Override
	public int add(Jieyuejilu po) {
		return gdao.insert(po);
	}

	@Override
	public int update(Jieyuejilu po) {
		return gdao.updateByPrimaryKeySelective(po);
	}

	
	
	@Override
	public int delete(int id) {
		return gdao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Jieyuejilu> getAll(Map<String, Object> map) {
		return gdao.getAll(map);
	}
	
	public List<Jieyuejilu> getsyjieyuejilu1(Map<String, Object> map) {
		return gdao.getsyjieyuejilu1(map);
	}
	public List<Jieyuejilu> getsyjieyuejilu2(Map<String, Object> map) {
		return gdao.getsyjieyuejilu2(map);
	}
	public List<Jieyuejilu> getsyjieyuejilu3(Map<String, Object> map) {
		return gdao.getsyjieyuejilu3(map);
	}
	
	@Override
	public Jieyuejilu quchongJieyuejilu(Map<String, Object> account) {
		return gdao.quchongJieyuejilu(account);
	}

	@Override
	public List<Jieyuejilu> getByPage(Map<String, Object> map) {
		return gdao.getByPage(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return gdao.getCount(map);
	}

	@Override
	public List<Jieyuejilu> select(Map<String, Object> map) {
		return gdao.select(map);
	}

	@Override
	public Jieyuejilu getById(int id) {
		return gdao.selectByPrimaryKey(id);
	}

}

