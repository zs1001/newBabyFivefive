package com.dooshen.newbabyfivefive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dooshen.newbabyfivefive.dao.LotteryDao;
import com.dooshen.newbabyfivefive.entity.Lottery;
import com.dooshen.newbabyfivefive.service.LotteryService;

@Service
@Transactional
public class LotteryServiceImpl implements LotteryService {
	
	@Autowired
	private LotteryDao lotteryDao;

	public Lottery findLotteryByExpect(String expect) {
		Lottery Lottery = lotteryDao.findLotteryByExpect(expect);
		return Lottery;
	}

}
