package com.dooshen.newbabyfivefive.dao;

import com.dooshen.newbabyfivefive.entity.Lottery;

public interface LotteryDao {
	public Lottery findLotteryByExpect(String expect);
}
