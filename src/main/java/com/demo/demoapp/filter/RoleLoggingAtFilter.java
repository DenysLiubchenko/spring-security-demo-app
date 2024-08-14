package com.demo.demoapp.filter;

import jakarta.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class RoleLoggingAtFilter implements Filter {

    private final Logger LOG =
        Logger.getLogger(RoleLoggingAtFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        LOG.info("Authentication Validation is in progress");
        chain.doFilter(request, response);
    }

}
