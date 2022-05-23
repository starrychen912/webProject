package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Tushuleibie;

public interface TushuleibieMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tushuleibie record);

    int insertSelective(Tushuleibie record);

    Tushuleibie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tushuleibie record);
	
    int updateByPrimaryKey(Tushuleibie record);
	public Tushuleibie quchongTushuleibie(Map<String, Object> leibie);
	public List<Tushuleibie> getAll(Map<String, Object> map);
	public List<Tushuleibie> getsytushuleibie1(Map<String, Object> map);
	public List<Tushuleibie> getsytushuleibie3(Map<String, Object> map);
	public List<Tushuleibie> getsytushuleibie2(Map<String, Object> map);
	public int getCount(Map<String, Object> po);
	public List<Tushuleibie> getByPage(Map<String, Object> map);
	public List<Tushuleibie> select(Map<String, Object> map);
//	所有List
}

