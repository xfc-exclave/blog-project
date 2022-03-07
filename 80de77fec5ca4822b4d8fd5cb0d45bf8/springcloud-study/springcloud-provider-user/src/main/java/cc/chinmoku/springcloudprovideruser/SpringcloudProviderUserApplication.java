package cc.chinmoku.springcloudprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProviderUserApplication.class, args);
    }

}
