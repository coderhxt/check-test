package com.coinceres.checktest.client;

import com.coinceres.proxy.api.MarginApi;
import org.springframework.cloud.netflix.feign.FeignClient;



@FeignClient(
        value = "proxy-server"
)
public interface MarginClient extends MarginApi {
}
