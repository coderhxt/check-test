package com.coinceres.checktest.controller;

import com.ceres.checkdata.api.APILimitFrequencyFeignApi;
import com.ceres.checkdata.api.OperatorApiLimitRuleServiceFeignApi;
import com.ceres.checkdata.pojo.CheckReq;
import com.coinceres.checktest.client.FutureTradeClient;
import com.coinceres.checktest.client.MarginClient;
import com.coinceres.checktest.client.RuleClient;
import com.coinceres.checktest.client.SpotClient;
import com.coinceres.exchange.model.enums.FutureType;
import com.coinceres.exchange.model.enums.PositionDir;
import com.coinceres.exchange.model.future.Account;
import com.coinceres.exchange.model.future.ContractPosition;
import com.coinceres.exchange.vo.AccountInfo;
import com.coinceres.exchange.vo.AccountInfoReq;
import com.coinceres.exchange.vo.BorrowReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import util.RpcResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RuleClient ruleClient;
    @Autowired
    private FutureTradeClient futureTradeClient;

    @GetMapping("future")
    public ResponseEntity<Void> futureTest() {
//        RpcResult<List<Account>> result = futureTradeClient.futureAccounts("OKEX", FutureType.DELIVERY_CONTRACT);
//        System.out.println("result = " + result);
        RpcResult<Map<PositionDir, ContractPosition>> result =
                futureTradeClient.contractPosition("OKEX", "BTC-USD-190510", FutureType.DELIVERY_CONTRACT);
        System.out.println(result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("test")
    public ResponseEntity testRuleApi() {
        CheckReq checkReq = new CheckReq();
//        checkReq.setEntrustDir("Buy");
//        checkReq.setEntrustType("limit");
        checkReq.setExchange("OKEX");
        checkReq.setPair("ADA_USDT");
//        checkReq.setPairType("bb");
        checkReq.setPrice(BigDecimal.valueOf(12000000));
        checkReq.setQuantity(BigDecimal.valueOf(10000000));
        RpcResult rpcResult = ruleClient.checkRule(checkReq);
        System.out.println(rpcResult.getCode());
        return ResponseEntity.ok(rpcResult);
}

    @Autowired
    private MarginClient marginClient;

    @GetMapping("margin")
    public ResponseEntity testMargin() {
        BorrowReq req = new BorrowReq();
        req.setExchangeCode("okex");
        req.setContractCode("btc_usdt");
        req.setCurrency("usdt");
        req.setAmount("3");
        RpcResult borrow;
        try {
            borrow = marginClient.borrow(req);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("失败");
        }
        return ResponseEntity.ok(borrow);
    }

    @Autowired
    private SpotClient spotClient;

    @GetMapping("spot")
    public ResponseEntity testSpot() {
        AccountInfoReq req = new AccountInfoReq();
        req.setExchangeCode("okex");
        req.setCurrency("btc");
        RpcResult<AccountInfo> result;
        try {
            result= this.spotClient.queryBbAccountBalance(req);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
        AccountInfo accountInfo = result.getResult();
        return ResponseEntity.ok(accountInfo);
    }

    @Autowired
    private APILimitFrequencyFeignApi apiLimitFrequencyFeignApi;

    @Autowired
    private OperatorApiLimitRuleServiceFeignApi operatorApiLimitRuleServiceFeignApi;


}
