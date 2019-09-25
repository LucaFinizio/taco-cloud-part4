package tacos.ingredientclient.resttemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Conditional(NotFeignAndNotWebClientCondition.class)
@Slf4j
public class RestTemplateConfig {

  @Bean
  @LoadBalanced   /*This annotation tells Spring Cloud to look up services through Ribbon.
  					Secondly, it acts as an injection qualifier, so that if you
					have two or more RestTemplate beans, you can specify that you want the load-balanced
					RestTemplate at the injection point.
  				  */
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  
  @Bean
  public CommandLineRunner startup() {
    return args -> {
      log.info("**************************************");
      log.info("    Configuring with RestTemplate");
      log.info("**************************************");
    };
  }
  
}