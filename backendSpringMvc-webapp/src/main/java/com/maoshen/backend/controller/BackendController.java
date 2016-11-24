package com.maoshen.backend.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maoshen.base.BaseController;

@Controller
@RequestMapping("/backend")
public class BackendController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BackendController.class);

	@RequestMapping(value = "/index", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(Model model) {
		LOGGER.info("backend index get,date is:{}", new Date());
		return "backend/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String msg, Model model) {
		return "backend/login";
	}
}
