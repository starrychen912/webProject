package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Jieyuejilu;

public interface JieyuejiluMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jieyuejilu record);

    int insertSelective(Jieyuejilu record);

    Jieyuejilu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jieyuejilu record);
	
    int updateByPrimaryKey(Jieyuejilu record);
	public Jieyuejilu quchongJieyuejilu(Map<String, Object> jieyueren);
	public List<Jieyuejilu> getAll(Map<String, Object> map);
	public List<Jieyuejilu> getsyjieyuejilu1(Map<String, Object> map);
	public List<Jieyuejilu> getsyjieyuejilu3(Map<String, Object> map);
	public List<Jieyuejilu> getsyjieyuejilu2(Map<String, Object> map);
	public int getCount(Map<String, Object> po);
	public List<Jieyuejilu> getByPage(Map<String, Object> map);
	public List<Jieyuejilu> select(Map<String, Object> map);
//	所有List
}

