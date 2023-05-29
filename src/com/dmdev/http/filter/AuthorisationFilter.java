package com.dmdev.http.filter;

import com.dmdev.http.dto.UserDto;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.dmdev.http.util.UrlPath.IMAGES;
import static com.dmdev.http.util.UrlPath.LOGIN;
import static com.dmdev.http.util.UrlPath.REGISTRATION;

@WebFilter("/*")
public class AuthorisationFilter implements Filter {
    private static final Set<String> PUBLIC_PATHS = Set.of(LOGIN, REGISTRATION, IMAGES);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse)
                    .sendRedirect(prevPage != null ? prevPage : LOGIN);
        }
    }

    private static boolean isUserLoggedIn(ServletRequest request) {
        UserDto userDto = (UserDto) ((HttpServletRequest) request).getSession().getAttribute("user");
        return userDto != null;
    }

    private static boolean isPublicPath(String uri) {
        return PUBLIC_PATHS.stream().anyMatch(uri::startsWith);
    }
}
