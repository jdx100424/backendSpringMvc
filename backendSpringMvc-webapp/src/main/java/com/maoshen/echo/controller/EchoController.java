package com.maoshen.echo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maoshen.base.disconf.BaseDisconf;
import com.maoshen.component.base.dto.ResponseResultDto;
import com.maoshen.component.controller.BaseController;
import com.maoshen.component.disconf.KafkaDisconf;
import com.maoshen.component.disconf.MysqlDisconf;
import com.maoshen.component.kafka.BaseProducer;
import com.maoshen.component.kafka.dto.MessageDto;
import com.maoshen.component.kafka.dto.MessageVo;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.dto.EchoDto;
import com.maoshen.echo.service.EchoService;

/**
 * 
 * @Description:
 * @author Daxian.jiang
 * @Email Daxian.jiang@vipshop.com
 * @Date 2015年7月14日 上午11:28:11
 * @Version V1.0
 */
@Controller
@RequestMapping("/echo")
public class EchoController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoController.class);

	@Autowired
	@Qualifier("echoServiceImpl")
	private EchoService echoService;

	@Autowired
	@Qualifier("baseProducer")
	private BaseProducer baseProducer;

	@Autowired
	private KafkaDisconf kafkaDisconf;

	@Autowired
	private MysqlDisconf mysqlDisconf;

	@Autowired
	private BaseDisconf baseDisconf;

	/**
	 * 
	 * @param request
	 * @param model
	 * @param src
	 * @return
	 */
	@RequestMapping(value = "check", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> echo(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boolean resultSelectOne = echoService.checkEchoIsExist(1L);
			boolean resultSelectTwo = echoService.checkEchoIsExist(2L);
			boolean resultSelectDubbo = echoService.checkDubbo(2L);
			Map<String, Object> dataResult = new HashMap<String, Object>();
			dataResult.put("1", resultSelectOne);
			dataResult.put("2", resultSelectTwo);
			dataResult.put("3", resultSelectDubbo);
			resultMap.put("echoHasResultSelect", dataResult);
		} catch (Exception e) {
			LOGGER.error("echo select error:", e);
			resultMap.put("echoHasResultSelect", e.getMessage());
		}

		try {
			Echo echo = new Echo();
			echo.setName(UUID.randomUUID().toString());
			echoService.insert(echo);
			resultMap.put("echoHasResultInsert", true);
		} catch (Exception e) {
			LOGGER.error("echo insert error:", e);
			resultMap.put("echoHasResultInsert", e.getMessage());
		}

		try {
			boolean result = echoService.checkRedis();
			resultMap.put("redisHasResult", result);
		} catch (Exception e) {
			LOGGER.error("redisService error:", e);
			resultMap.put("redisHasResult", e.getMessage());
		}

		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("jdx", UUID.randomUUID().toString());
			MessageDto dto = new MessageDto(sendMap,UUID.randomUUID().toString());
			baseProducer.send(MessageVo.ECHO_MESSAGE.getTopicName(), dto);
			resultMap.put("kakfaResult", true);
		} catch (Exception e) {
			LOGGER.error("kakfaService error:", e);
			resultMap.put("kakfaResult", e.getMessage());
		}

		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("kafkaIp", kafkaDisconf.getKafkaIp());
			sendMap.put("kafkaPort", kafkaDisconf.getKafkaPort());
			sendMap.put("MysqlMasterUsername", mysqlDisconf.getMysqlMasterUsername());
			sendMap.put("MysqlMasterPort", mysqlDisconf.getMysqlMasterPort());
			resultMap.put("disconfResult", sendMap);
		} catch (Exception e) {
			LOGGER.error("disconfResult error:", e);
			resultMap.put("disconfResult", e.getMessage());
		}

		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}

	@RequestMapping(value = "listData", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> listData(HttpServletRequest request, Model model) {
		Integer page = 1;
		try{
			page = Integer.parseInt(request.getParameter("page"));	
			if(page < 1){
				page = 1;
			}
		}catch(Exception e){
			
		}
		
		Integer count = EchoService.DEFAULT_COUNT;
		try{
			count = Integer.parseInt(request.getParameter("count"));	
			if(count < 1){
				count = EchoService.DEFAULT_COUNT;
			}
		}catch(Exception e){
			
		}
		
		EchoDto echoDto = new EchoDto();
		echoDto.setCount(count);
		echoDto.setPage(page);
		String name = request.getParameter("name");
		if(StringUtils.isNotBlank(name) && "".equals(name.trim())==false){
			echoDto.setName(name);
		}
		try{
			int id = Integer.parseInt(request.getParameter("id"));	
			echoDto.setId(id);
		}catch(Exception e){
			
		}
		
		List<Echo> resultList = echoService.getList(echoDto);
		int total = echoService.getCount(echoDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("echoList", resultList);
		resultMap.put("page", page);
		resultMap.put("count", count);
		resultMap.put("total", total);
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	
	@RequestMapping(value = "/listPage", method = { RequestMethod.POST, RequestMethod.GET })
	public String listPage(HttpServletRequest request, Model model) {
		setProjectUrl(model, baseDisconf.getProjectUrl());
		model.addAttribute("count", EchoService.DEFAULT_COUNT);
		return "echo/list";
	}
	
	/**
	 * 
	 * @Description: 登录首页入口
	 * @author Daxian.jiang
	 * @Email Daxian.jiang@vipshop.com
	 * @Date 2015年9月24日 上午9:37:35
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(HttpServletRequest request, Model model) {
		setProjectUrl(model, baseDisconf.getProjectUrl());

		try {
			boolean resultSelectOne = echoService.checkEchoIsExist(1L);
			boolean resultSelectTwo = echoService.checkEchoIsExist(2L);
			boolean resultSelectDubbo = echoService.checkDubbo(2L);
			Map<String, Object> dataResult = new HashMap<String, Object>();
			dataResult.put("1", resultSelectOne);
			dataResult.put("2", resultSelectTwo);
			dataResult.put("3", resultSelectDubbo);
			model.addAttribute("echoHasResultSelect", dataResult);
		} catch (Exception e) {
			LOGGER.error("echo select error:", e);
			model.addAttribute("echoHasResultSelect", e.getMessage());
		}

		try {
			Echo echo = new Echo();
			echo.setName(UUID.randomUUID().toString());
			echoService.insert(echo);
			model.addAttribute("echoHasResultInsert", true);
		} catch (Exception e) {
			LOGGER.error("echo insert error:", e);
			model.addAttribute("echoHasResultInsert", e.getMessage());
		}

		try {
			boolean result = echoService.checkRedis();
			model.addAttribute("redisHasResult", result);
		} catch (Exception e) {
			LOGGER.error("redisService error:", e);
			model.addAttribute("redisHasResult", e.getMessage());
		}

		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("jdx", UUID.randomUUID().toString());
			MessageDto dto = new MessageDto(sendMap,UUID.randomUUID().toString());
			baseProducer.send(MessageVo.ECHO_MESSAGE.getTopicName(), dto);
			model.addAttribute("kakfaResult", true);
		} catch (Exception e) {
			LOGGER.error("kakfaService error:", e);
			model.addAttribute("kakfaResult", e.getMessage());
		}

		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("kafkaIp", kafkaDisconf.getKafkaIp());
			sendMap.put("kafkaPort", kafkaDisconf.getKafkaPort());
			sendMap.put("MysqlMasterUsername", mysqlDisconf.getMysqlMasterUsername());
			sendMap.put("MysqlMasterPort", mysqlDisconf.getMysqlMasterPort());
			model.addAttribute("disconfResult", sendMap);
		} catch (Exception e) {
			LOGGER.error("disconfResult error:", e);
			model.addAttribute("disconfResult", e.getMessage());
		}
		return "echo/index";
	}
	
	public void setProjectUrl(Model model,String projectUrl){
		LOGGER.info("projectUrl:{}",projectUrl);
		model.addAttribute("projectUrl", projectUrl);
	}

	@Override
	public String getServiceName() {
		return "EchoController";
	}
}
