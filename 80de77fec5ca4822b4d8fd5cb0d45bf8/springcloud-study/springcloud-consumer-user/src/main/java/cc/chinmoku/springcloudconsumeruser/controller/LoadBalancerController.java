package cc.chinmoku.springcloudconsumeruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class LoadBalancerController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String NAME_SERVICE_PREGIX = "http://SPRINGCLOUD-PROVIDER-USER";

    @ResponseBody
    @GetMapping("/user/{id}")
    public Object balancerWay(@PathVariable String id) {
        return this.webClientBuilder.build().get().uri(NAME_SERVICE_PREGIX + "/user/detail/" + id).retrieve().bodyToMono(Object.class);
    }

}
