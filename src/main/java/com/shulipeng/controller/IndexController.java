package com.shulipeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/26
 */
@Controller
public class IndexController {

    /**
     * 进入首页
     * @return
     */
    @GetMapping("/index")
    String toIndex(){
        return "index";
    }

    /**
     * 进入首页
     * @return
     */
    @GetMapping("/main")
    String toMain(){
        return "main";
    }
}
