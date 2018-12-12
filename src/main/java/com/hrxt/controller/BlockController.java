package com.hrxt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrxt.service.BlockService;

@Controller
@RequestMapping("/block")
public class BlockController {

	@Autowired
	private BlockService blockService;
	
	
	//  /block/getBlock
	@RequestMapping("/getBlock")
	@ResponseBody
	public String getWell(String orgId){
		return blockService.callWellList(orgId);
	}
	
	
	
	
	
}
