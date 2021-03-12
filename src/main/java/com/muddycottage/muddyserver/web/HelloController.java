package com.muddycottage.muddyserver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static int count = 0 ;
    private static int smsCount = 2000000 ;

    private final Logger logger = LoggerFactory.getLogger(HelloController.class) ;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        count++ ;

        String outStr = String.format ("Hello %d", count) ;
        System.out.println(outStr) ;
        return new ResponseEntity<>(outStr, HttpStatus.OK) ;
    }

    @RequestMapping(value = "api-adv.php", method = RequestMethod.POST)
    public ResponseEntity<String> smsApi(String username, String password, String to) {
        smsCount++ ;

        String outStr ;

        if ("badUser".equals(username)) {
            outStr = "ERROR:Username or password is incorrect. " ;
        }
        else if ("123".equals(to)) {
            outStr = String.format("BAD:%s", to)  ;
        }
        else {
            outStr = String.format("OK:%s:%07d", to, smsCount);
        }

        System.out.println(outStr) ;
        return new ResponseEntity<>(outStr, HttpStatus.OK) ;
    }
}
