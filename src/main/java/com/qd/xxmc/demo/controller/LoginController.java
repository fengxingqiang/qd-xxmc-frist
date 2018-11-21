package com.qd.xxmc.demo.controller;

import com.qd.xxmc.demo.service.MultiThreadService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MultiThreadService multiThreadService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        String sql = "select id from test where id='1' ";
        String id = jdbcTemplate.queryForObject(sql, String.class);
        return "hello world" + id;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2() {
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("ss");
        multiThreadService.multiThread(list);
        return "hello world" ;
    }
}
