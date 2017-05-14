package os;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cheng on 2017/5/12.
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ConsulClientApp {

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/discover")
  public Object discover() {
      return loadBalancerClient.choose("shengnianos-consul-blog").getUri().toString();
  }

  @RequestMapping("services")
  public Object services() {
      return discoveryClient.getInstances("shengnianos-consul-person");
  }


   public static void main(String[] args) {
     SpringApplication.run(ConsulClientApp.class, args);
   }

}

