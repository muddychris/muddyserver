package com.muddycottage.muddyserver.web;

import com.muddycottage.muddyserver.dto.DataFeedDto;
import com.muddycottage.muddyserver.service.DataFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataFeedController {
	
	@Autowired
	private DataFeedService dataFeedService ;
	
	private final Logger logger = LoggerFactory.getLogger(DataFeedController.class) ;
	

	@RequestMapping(value = "datafeed", method = RequestMethod.POST)
	public ResponseEntity<String>  addDataFeed (@RequestBody DataFeedDto dataFeedDto) {

		System.out.println("datafeed") ;
		dataFeedService.processDataFeed(dataFeedDto);
		
		// return response
		
		return new ResponseEntity<>("data saved", HttpStatus.OK) ;
	}
}
