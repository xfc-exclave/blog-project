package cc.chinmoku.springcloudconsumeruserribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    private final String NAME_SERVICE_PREGIX = "http://springcloud-provider-user";

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @GetMapping("/user/{id}")
    public Object ribbonWay(@PathVariable String id) {
        System.out.println(">>>>>>>>>>>>>>>> ribbon user controller");
        return this.restTemplate.getForEntity(NAME_SERVICE_PREGIX + "/user/detail/" + id, String.class);
    }

}
