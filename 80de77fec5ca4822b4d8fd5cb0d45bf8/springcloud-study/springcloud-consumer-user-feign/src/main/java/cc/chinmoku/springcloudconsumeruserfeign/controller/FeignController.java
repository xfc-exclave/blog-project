package cc.chinmoku.springcloudconsumeruserfeign.controller;

import cc.chinmoku.springcloudconsumeruserfeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FeignController {

    private final String NAME_SERVICE_PREGIX = "http://springcloud-provider-user";

    @Autowired
    private FeignService feignService;

    @ResponseBody
    @GetMapping("/user/{id}")
    public Object ribbonWay(@PathVariable String id) {
        System.out.println(">>>>>>>>>>>>>>>> feign user controller");
        return this.feignService.findUserById(id);
    }

}
