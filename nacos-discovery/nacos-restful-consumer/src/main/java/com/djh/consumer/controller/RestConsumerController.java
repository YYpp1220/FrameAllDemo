package com.djh.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * 其他消费者控制器
 *
 * @author MrMyHui
 * @date 2021/04/02
 */
@RestController
public class RestConsumerController {
    @Autowired(required = false)
    private RestTemplate restTemplate;

    /**
     *
     *  服务id即注册中心的中的服务名
     */
    private static final String NACOS_SERVICE_ID="nacos-restful-provider";

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${provider.address}")
    private String providerAddress;

    private static final String HTTP_TOP = "http://";

    private static final String HTTP_TAIL = "/service";

    @GetMapping("/service")
    public String service () {
        // String providerStr = restTemplate.getForObject(HTTP_TOP + providerAddress + HTTP_TAIL, String.class);
        ServiceInstance serviceInstance = loadBalancerClient.choose(NACOS_SERVICE_ID);
        URI uri = serviceInstance.getUri();
        String providerStr = restTemplate.getForObject(uri + HTTP_TAIL, String.class);
        System.out.println("consumer | invoke" + providerStr);
        return "consumer | invoke" + providerStr;
    }
}
