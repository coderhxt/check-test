package com.coinceres.checktest.client;

import com.ceres.checkdata.api.RuleApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("check-server")
public interface RuleClient extends RuleApi {
}
