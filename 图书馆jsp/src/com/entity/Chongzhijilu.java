package com.entity;

public class Chongzhijilu {
    private Integer id;
	private String xuehao;	private String xingming;	private String yue;	private String chongzhijine;	private String beizhu;	
    private String addtime;

    

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
	
	public String getXuehao() {
        return xuehao;
    }
    public void setXuehao(String xuehao) {
        this.xuehao = xuehao == null ? null : xuehao.trim();
    }	public String getXingming() {
        return xingming;
    }
    public void setXingming(String xingming) {
        this.xingming = xingming == null ? null : xingming.trim();
    }	public String getYue() {
        return yue;
    }
    public void setYue(String yue) {
        this.yue = yue == null ? null : yue.trim();
    }	public String getChongzhijine() {
        return chongzhijine;
    }
    public void setChongzhijine(String chongzhijine) {
        this.chongzhijine = chongzhijine == null ? null : chongzhijine.trim();
    }	public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }	
	
	
    public String getAddtime() {
        return addtime;
    }
    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
}
//   设置字段信息
