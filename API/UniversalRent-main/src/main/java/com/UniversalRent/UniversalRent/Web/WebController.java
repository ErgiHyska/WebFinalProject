package com.UniversalRent.UniversalRent.Web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @GetMapping("/post")
    public String showPost() {
        return "post";
    }

    @GetMapping("/showroom")
    public String showShowroom() {
        return "showroom";
    }
}
