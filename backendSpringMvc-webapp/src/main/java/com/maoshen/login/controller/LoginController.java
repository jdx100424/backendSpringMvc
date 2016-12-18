package com.maoshen.login.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maoshen.component.controller.BaseController;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String msg, Model model) {
		LOGGER.info("backend login get,date is:{}", new Date());
		return "login";
	}

	@Override
	public String getServiceName() {
		return "LoginController";
	}
}
