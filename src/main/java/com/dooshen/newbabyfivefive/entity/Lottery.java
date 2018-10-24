package com.dooshen.newbabyfivefive.entity;

import java.util.Date;

public class Lottery {
	//开奖期号
	private String expect;
	//开奖号码
	private String opencode;
	//时间戳
	private String opentimestamp;
	//开奖时间
	private Date opentime;
	
	
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public String getOpencode() {
		return opencode;
	}
	public void setOpencode(String opencode) {
		this.opencode = opencode;
	}
	public String getOpentimestamp() {
		return opentimestamp;
	}
	public void setOpentimestamp(String opentimestamp) {
		this.opentimestamp = opentimestamp;
	}
	public Date getOpentime() {
		return opentime;
	}
	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
}
