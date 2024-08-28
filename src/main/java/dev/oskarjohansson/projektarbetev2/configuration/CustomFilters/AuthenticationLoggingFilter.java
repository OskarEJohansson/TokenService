package dev.oskarjohansson.projektarbetev2.configuration.CustomFilters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            String userName = authentication.getName();
            LOG.debug("Successfully authenticated with user {}", userName);
        }


        filterChain.doFilter(request,response);

    }
}
