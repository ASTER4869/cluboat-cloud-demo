package com.cluboat.springcloud.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)
public class UserController {
}
