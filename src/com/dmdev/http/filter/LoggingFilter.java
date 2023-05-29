package com.dmdev.http.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@WebFilter("/*")
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Parameter Map:");
        servletRequest.getParameterMap().forEach(
                (k, v) -> System.out.printf("%s: %s\n", k, Arrays.toString(v))
        );

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println(session.getAttribute(attributeNames.nextElement()));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
