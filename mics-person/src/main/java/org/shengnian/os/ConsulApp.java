package org.shengnian.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cheng on 2017/5/12.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ConsulApp {
//    @Autowired
//    private Environment environment;

    @RequestMapping("/hello")
    public Object hello(@RequestParam String name) {
        return "Hello " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulApp.class, args);
    }

}
