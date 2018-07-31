package com.springboot.demo;


import com.springboot.domain.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
public class HelloWorldController {

    @Autowired
    private User user;

    @RequestMapping("**/user")
    public User testPojo(User user) {
        return user;
    }

    @RequestMapping(value = "*/index/{id}")
    public Object index(@PathVariable("id") String id, @RequestParam(required = false) String name) {
        if (id == "1") {
            return null;
        }
        // return user.toString() + ":" + id + name;
        return null;

    }

    @RequestMapping(value = "**/get/{id}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable String id) {
        return "get请求:" + id;
    }

    @RequestMapping(value = "**/post", method = RequestMethod.POST)
    public String tetRequestPost() {
        return "post请求";
    }

    @RequestMapping(value = "**/delete/{id}", method = RequestMethod.DELETE)
    public String tetRequestDelete(@PathVariable String id) {
        return "Delete:" + id;
    }

    @RequestMapping(value = "**/put/{id}", method = RequestMethod.PUT)
    public String tetRequestPut(@PathVariable String id) {
        return "Delete:" + id;
    }

    @RequestMapping(value = "**/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID", required = false) String cookieValue) {
        System.out.println("testCookieValue: " + cookieValue);
        return cookieValue;
    }


}
