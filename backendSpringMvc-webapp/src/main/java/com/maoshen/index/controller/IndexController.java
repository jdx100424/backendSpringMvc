package com.maoshen.index.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maoshen.base.BaseController;
import com.maoshen.base.disconf.BaseDisconf;
import com.maoshen.component.other.ResourceUtils;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private BaseDisconf baseDisconf;
	
	@RequestMapping(value = "/index", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(Model model) {
		setProjectUrl(model, baseDisconf.getProjectUrl());
		LOGGER.info("backend index get,date is:{}", new Date());
		return "index";
	}
}
