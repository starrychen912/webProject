package com.server;

import java.util.List;

import java.util.Map;

import com.entity.Tushuleibie;

public interface TushuleibieServer {

  public int add(Tushuleibie po);

  public int update(Tushuleibie po);
  
  
  
  public int delete(int id);

  public List<Tushuleibie> getAll(Map<String,Object> map);
  public List<Tushuleibie> getsytushuleibie1(Map<String,Object> map);
  public List<Tushuleibie> getsytushuleibie2(Map<String,Object> map);
  public List<Tushuleibie> getsytushuleibie3(Map<String,Object> map);
  public Tushuleibie quchongTushuleibie(Map<String, Object> acount);

  public Tushuleibie getById( int id);

  public List<Tushuleibie> getByPage(Map<String, Object> map);

  public int getCount(Map<String,Object> map);

  public List<Tushuleibie> select(Map<String, Object> map);
}
//	所有List
