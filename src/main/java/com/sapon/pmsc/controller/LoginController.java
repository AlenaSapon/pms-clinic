package com.sapon.pmsc.controller;

import com.sapon.pmsc.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class LoginController {

    //get login form page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        //return html
        return "login";
    }

    //checking for login credentials
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:api/v1/patients";
        } else
            model.addAttribute("invalidCredentials", true);
        return "login";
    }
}
