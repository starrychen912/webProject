package com.server;

import java.util.List;

import java.util.Map;

import com.entity.Jieyuejilu;

public interface JieyuejiluServer {

  public int add(Jieyuejilu po);

  public int update(Jieyuejilu po);
  
  
  
  public int delete(int id);

  public List<Jieyuejilu> getAll(Map<String,Object> map);
  public List<Jieyuejilu> getsyjieyuejilu1(Map<String,Object> map);
  public List<Jieyuejilu> getsyjieyuejilu2(Map<String,Object> map);
  public List<Jieyuejilu> getsyjieyuejilu3(Map<String,Object> map);
  public Jieyuejilu quchongJieyuejilu(Map<String, Object> acount);

  public Jieyuejilu getById( int id);

  public List<Jieyuejilu> getByPage(Map<String, Object> map);

  public int getCount(Map<String,Object> map);

  public List<Jieyuejilu> select(Map<String, Object> map);
}
//	所有List
