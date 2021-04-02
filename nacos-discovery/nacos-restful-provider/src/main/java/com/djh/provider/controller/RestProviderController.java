package com.djh.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 其他提供程序控制器
 *
 * @author MrMyHui
 * @date 2021/04/02
 */
@RestController
public class RestProviderController {
    @GetMapping("/service")
    public String service () {
        System.out.println("provider | invoke");
        return "provider | invoke";
    }
}
