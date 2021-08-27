package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/house")
public class HouseController {
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	@Autowired
	private HouseMapService houseMapService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "index";
	}
	@RequestMapping(value = "/sido", method = RequestMethod.GET)
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		List<SidoGugunCodeDto> list = houseMapService.getSido();
		return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gugun/{sidoCode}", method = RequestMethod.GET)
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@PathVariable("sidoCode") String sido) throws Exception {
		List<SidoGugunCodeDto> list = houseMapService.getGugunInSido(sido);
		return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
	
	}
	@RequestMapping(value = "/dong/{gugunCode}", method = RequestMethod.GET)
	public ResponseEntity<List<HouseInfoDto>> dong(@PathVariable("gugunCode") String gugun) throws Exception {
		List<HouseInfoDto> list = houseMapService.getDongInGugun(gugun);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	
	}
	@RequestMapping(value = "/apt/{dong}", method = RequestMethod.GET)
	public ResponseEntity<List<HouseInfoDto>> apt(@PathVariable("dong") String dong) throws Exception {
		List<HouseInfoDto> list = houseMapService.getAptInDong(dong);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	
	}
	//idx.jsp Line:221 callHouseDealInfo(aptName) method-> google map의 marker 클릭 시 아파트 실거래 정보 출력
	@RequestMapping(value = "/deal/{aptName}", method = RequestMethod.GET)
	public ResponseEntity<HouseDealDto> deal(@PathVariable("aptName") String aptName) throws Exception {
		HouseDealDto houseDealDto = houseMapService.getAptInfo(aptName);
		return new ResponseEntity<HouseDealDto>(houseDealDto, HttpStatus.OK);
	
	}
}
