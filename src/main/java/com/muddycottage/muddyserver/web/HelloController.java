package com.muddycottage.muddyserver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private static int count = 0 ;
    private static int smsCount = 2000000 ;

    private final Logger logger = LoggerFactory.getLogger(HelloController.class) ;

    @GetMapping(value = "")
    public ResponseEntity<String> home() {
        count++ ;

        String outStr = String.format ("Home %d", count) ;
        logger.info(outStr) ;
        return new ResponseEntity<>(outStr, HttpStatus.OK) ;
    }

    @GetMapping(value = "hello")
    public ResponseEntity<String> hello() {
        count++ ;

        String outStr = String.format ("Hello %d", count) ;
        logger.info(outStr) ;
        return new ResponseEntity<>(outStr, HttpStatus.OK) ;
    }

    @PostMapping(value = "api-adv.php")
    public ResponseEntity<String> smsApi(String username, String password, String to) {
        smsCount++ ;

        String outStr ;

        if ("badUser".equals(username)) {
            outStr = "ERROR:Username or password is incorrect. " ;
        }
        else if ("123".equals(to)) {
            outStr = String.format("BAD:%s", to)  ;
        }
        else if ("456".equals(to)) {
            outStr = String.format("ERR:%s", to)  ;
            return new ResponseEntity<>(outStr, HttpStatus.BAD_REQUEST) ;
        }
        else {
            outStr = String.format("OK:%s:%07d", to, smsCount);
        }

        if ("111".equals(to)) {
            logger.info("sleeping");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                logger.info("Sleep interrupted") ;
            }
        }
        System.out.println(outStr) ;
        return new ResponseEntity<>(outStr, HttpStatus.OK) ;
    }
}
