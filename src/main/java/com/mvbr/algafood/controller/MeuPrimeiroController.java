package com.mvbr.algafood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "O Senhor Jesus Cristo é o meu Pastor e nada me faltará!!!";
    }
}
