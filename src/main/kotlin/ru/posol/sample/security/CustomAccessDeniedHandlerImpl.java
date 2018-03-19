package ru.posol.sample.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class for custom error response if User does not have access
 */
public class CustomAccessDeniedHandlerImpl implements AccessDeniedHandler {

    protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);
    private String errorPage;
    private static final String MESSAGE = "User does not have access";

    public CustomAccessDeniedHandlerImpl() {
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (!response.isCommitted()) {
           response.setStatus(HttpStatus.FORBIDDEN.value());
           response.setContentType("application/json");
           response.getOutputStream().println("{ \"message\": \"" + MESSAGE + "\" }");
        }

    }


}