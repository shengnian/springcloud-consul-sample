package os;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cheng on 2017/5/12.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableFeignClients
public class ConsulClientApp {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

//    @Value("${spring.application.name:defaultAppName}")
//    private String appName;

    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancerClient.choose("shengnianos-consul-blog").getUri().toString();
    }

    @RequestMapping("services")
    public Object services() {
        return discoveryClient.getInstances("shengnianos-consul-person");
    }

    @RequestMapping(value = "/ribbon-hello", method = RequestMethod.GET)
    public String ribbonHello() {
        return restTemplate()
                .getForEntity("http://shengnianos-consule-person:8082/hello?name=shengnian ribbon", String.class)
                .getBody();
    }


    @Autowired
    private SampleClient sampleClient;

    @RequestMapping(value = "/feign-hello", method = RequestMethod.GET)
    public String feignHello() {
        return sampleClient.hello("Shengnian feign");
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulClientApp.class, args);
   }

   @Bean
    RestTemplate restTemplate() {
      return new RestTemplate();
   }

    @FeignClient("shengnianos-consule-person:8082")
    public interface SampleClient {

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        String hello(@RequestParam(name = "name") String name);
    }
}

