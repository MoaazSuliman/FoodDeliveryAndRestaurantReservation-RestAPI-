package com.moaaz.resturant.security;

import jakarta.servlet.http.HttpSession;

public class SessionSecurity {

    public  boolean adminSecurity(HttpSession session) {
        return (session.getAttribute("adminId") == null) ? false : true;
    }

    public  boolean userSecurity(HttpSession session) {
        return (session.getAttribute("userId") == null) ? false : true;
    }
}
