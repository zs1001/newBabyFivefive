package com.dooshen.newbabyfivefive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dooshen.newbabyfivefive.entity.Lottery;
import com.dooshen.newbabyfivefive.service.LotteryService;

@Controller
public class LotteryController {
	
	@Autowired
	private LotteryService lotteryService;
	
	/**
	 * 
	 */
	@RequestMapping("/findLotteryByExpect")
	public Lottery findLotteryByExpect(String expect) {
		Lottery lottery = lotteryService.findLotteryByExpect(expect);
		return lottery;
	}
}
