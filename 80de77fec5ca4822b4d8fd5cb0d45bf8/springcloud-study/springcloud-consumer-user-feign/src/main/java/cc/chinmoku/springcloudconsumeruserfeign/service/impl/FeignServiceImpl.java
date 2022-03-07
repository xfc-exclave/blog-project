package cc.chinmoku.springcloudconsumeruserfeign.service.impl;

import cc.chinmoku.springcloudconsumeruserfeign.service.FeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceImpl implements FeignService {

    @Override
    public String findUserById(String id) {
        return "Sorry, there were some errors in this micro-service!";
    }
}
