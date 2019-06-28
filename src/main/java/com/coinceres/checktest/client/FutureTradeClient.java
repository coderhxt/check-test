package com.coinceres.checktest.client;

import com.coinceres.proxy.api.FutureTradeApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author xiaotian.huang
 * @date 2019-04-19
 */
@FeignClient(
        value = "proxy-server"
)
public interface FutureTradeClient extends FutureTradeApi {
}
