package com.shulipeng.controller;

import com.shulipeng.domain.Table;
import com.shulipeng.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author pengxianyang
 * @Description:
 * @date 2018/3/25
 */
@Controller
public class GeneratorController {

    private static final String prefix = "/generator";

    @Autowired
    GeneratorService generatorService;

    @GetMapping("")
    String generator(){
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    List<Table> list(){
        return generatorService.list();
    }

}
