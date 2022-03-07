package cc.chinmoku.springcloudconsumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConsumerUserApplication.class, args);
    }

}
