package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Chongzhijilu;

public interface ChongzhijiluMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chongzhijilu record);

    int insertSelective(Chongzhijilu record);

    Chongzhijilu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chongzhijilu record);
	
    int updateByPrimaryKey(Chongzhijilu record);
	public Chongzhijilu quchongChongzhijilu(Map<String, Object> xuehao);
	public List<Chongzhijilu> getAll(Map<String, Object> map);
	public List<Chongzhijilu> getsychongzhijilu1(Map<String, Object> map);
	public List<Chongzhijilu> getsychongzhijilu3(Map<String, Object> map);
	public List<Chongzhijilu> getsychongzhijilu2(Map<String, Object> map);
	public int getCount(Map<String, Object> po);
	public List<Chongzhijilu> getByPage(Map<String, Object> map);
	public List<Chongzhijilu> select(Map<String, Object> map);
//	所有List
}

