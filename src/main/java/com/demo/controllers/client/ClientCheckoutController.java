package com.demo.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class ClientCheckoutController {

    @GetMapping("/checkout")
    public String getCheckout(Model model, HttpSession session) {
        return "/client/checkout";
    }

}
