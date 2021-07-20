package com.example.demo.model.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@CrossOrigin(origins = "*")
public class StaticPagesController {

    @GetMapping("/about")
    @ResponseBody
    public ModelAndView aboutPage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @GetMapping("/woe")
    @ResponseBody
    public ModelAndView woePage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("woe");
        return modelAndView;
    }

    @GetMapping("/contact")
    @ResponseBody
    public ModelAndView contactPage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @GetMapping("/faq")
    @ResponseBody
    public ModelAndView faqPage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("faq");
        return modelAndView;
    }



}
