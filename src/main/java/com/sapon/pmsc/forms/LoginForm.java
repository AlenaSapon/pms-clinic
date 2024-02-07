package com.sapon.pmsc.forms;

import org.springframework.stereotype.Component;

public class LoginForm {
     private String username;
    private String password;
    public LoginForm() {
        super();
    }
    public String getUsername() {
               return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
