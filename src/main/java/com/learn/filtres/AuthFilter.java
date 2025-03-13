package com.learn.filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/Dashboard.jsp","/Dashboard.jsp"})

public class AuthFilter implements Filter {
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("utilisateur") != null);
        String loginURI = req.getContextPath() + "/login.jsp";

        if (!isLoggedIn) {
            res.sendRedirect(loginURI);
        } else {
            chain.doFilter(request, response);
        }
    }
}
