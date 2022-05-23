package com.server;

import java.util.List;

import java.util.Map;

import com.entity.Chongzhijilu;

public interface ChongzhijiluServer {

  public int add(Chongzhijilu po);

  public int update(Chongzhijilu po);
  
  
  
  public int delete(int id);

  public List<Chongzhijilu> getAll(Map<String,Object> map);
  public List<Chongzhijilu> getsychongzhijilu1(Map<String,Object> map);
  public List<Chongzhijilu> getsychongzhijilu2(Map<String,Object> map);
  public List<Chongzhijilu> getsychongzhijilu3(Map<String,Object> map);
  public Chongzhijilu quchongChongzhijilu(Map<String, Object> acount);

  public Chongzhijilu getById( int id);

  public List<Chongzhijilu> getByPage(Map<String, Object> map);

  public int getCount(Map<String,Object> map);

  public List<Chongzhijilu> select(Map<String, Object> map);
}
//	所有List
