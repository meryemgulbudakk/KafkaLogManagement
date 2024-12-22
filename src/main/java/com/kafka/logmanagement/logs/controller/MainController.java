package com.kafka.logmanagement.logs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests to the main page.
 */
@Controller
public class MainController {

    /**
     * Displays the main page.
     *
     * @return the name of the view to render
     */
    @GetMapping("/api")
    public String showMainPage() {
        System.out.println("Main Controller");
        return "index";
    }
}
