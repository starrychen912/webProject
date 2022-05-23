package com.server.impl;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.TushuleibieMapper;
import com.entity.Tushuleibie;
import com.server.TushuleibieServer;
@Service
public class TushuleibieServerImpi implements TushuleibieServer {
   @Resource
   private TushuleibieMapper gdao;
	@Override
	public int add(Tushuleibie po) {
		return gdao.insert(po);
	}

	@Override
	public int update(Tushuleibie po) {
		return gdao.updateByPrimaryKeySelective(po);
	}

	
	
	@Override
	public int delete(int id) {
		return gdao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Tushuleibie> getAll(Map<String, Object> map) {
		return gdao.getAll(map);
	}
	
	public List<Tushuleibie> getsytushuleibie1(Map<String, Object> map) {
		return gdao.getsytushuleibie1(map);
	}
	public List<Tushuleibie> getsytushuleibie2(Map<String, Object> map) {
		return gdao.getsytushuleibie2(map);
	}
	public List<Tushuleibie> getsytushuleibie3(Map<String, Object> map) {
		return gdao.getsytushuleibie3(map);
	}
	
	@Override
	public Tushuleibie quchongTushuleibie(Map<String, Object> account) {
		return gdao.quchongTushuleibie(account);
	}

	@Override
	public List<Tushuleibie> getByPage(Map<String, Object> map) {
		return gdao.getByPage(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return gdao.getCount(map);
	}

	@Override
	public List<Tushuleibie> select(Map<String, Object> map) {
		return gdao.select(map);
	}

	@Override
	public Tushuleibie getById(int id) {
		return gdao.selectByPrimaryKey(id);
	}

}

