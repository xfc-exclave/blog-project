package cc.chinmoku.springcloudconsumeruserfeign.service;

import cc.chinmoku.springcloudconsumeruserfeign.service.impl.FeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "SPRINGCLOUD-PROVIDER-USER", fallback = FeignServiceImpl.class)
public interface FeignService {

    @GetMapping(value = "/user/detail/{id}")
    String findUserById(@PathVariable(value = "id") String id);
}
