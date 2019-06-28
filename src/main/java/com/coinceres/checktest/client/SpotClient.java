package com.coinceres.checktest.client;

import com.coinceres.proxy.api.SpotApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(
        value = "proxy-server"
)
public interface SpotClient extends SpotApi {
}
