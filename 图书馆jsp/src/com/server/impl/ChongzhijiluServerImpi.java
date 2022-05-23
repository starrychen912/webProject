package com.server.impl;

import java.util.List;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ChongzhijiluMapper;
import com.entity.Chongzhijilu;
import com.server.ChongzhijiluServer;
@Service
public class ChongzhijiluServerImpi implements ChongzhijiluServer {
   @Resource
   private ChongzhijiluMapper gdao;
	@Override
	public int add(Chongzhijilu po) {
		return gdao.insert(po);
	}

	@Override
	public int update(Chongzhijilu po) {
		return gdao.updateByPrimaryKeySelective(po);
	}

	
	
	@Override
	public int delete(int id) {
		return gdao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Chongzhijilu> getAll(Map<String, Object> map) {
		return gdao.getAll(map);
	}
	
	public List<Chongzhijilu> getsychongzhijilu1(Map<String, Object> map) {
		return gdao.getsychongzhijilu1(map);
	}
	public List<Chongzhijilu> getsychongzhijilu2(Map<String, Object> map) {
		return gdao.getsychongzhijilu2(map);
	}
	public List<Chongzhijilu> getsychongzhijilu3(Map<String, Object> map) {
		return gdao.getsychongzhijilu3(map);
	}
	
	@Override
	public Chongzhijilu quchongChongzhijilu(Map<String, Object> account) {
		return gdao.quchongChongzhijilu(account);
	}

	@Override
	public List<Chongzhijilu> getByPage(Map<String, Object> map) {
		return gdao.getByPage(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return gdao.getCount(map);
	}

	@Override
	public List<Chongzhijilu> select(Map<String, Object> map) {
		return gdao.select(map);
	}

	@Override
	public Chongzhijilu getById(int id) {
		return gdao.selectByPrimaryKey(id);
	}

}

