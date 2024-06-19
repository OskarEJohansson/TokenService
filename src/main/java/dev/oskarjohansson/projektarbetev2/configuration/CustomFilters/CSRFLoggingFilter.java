package dev.oskarjohansson.projektarbetev2.configuration.CustomFilters;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

public class CSRFLoggingFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(CSRFLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        CsrfToken token = (CsrfToken) servletRequest.getAttribute("_csrf");
        LOG.info("CSRF token " + token.getToken());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
