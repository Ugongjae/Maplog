package com.maplog.b.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
//        ErrorResponse er = null;
        if(InsufficientAuthenticationException.class == authException.getClass()) {
//            er = new ErrorResponse(ErrorCode.UNAUTHORIZED, authException);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getOutputStream().println(objectMapper.writeValueAsString(er));
    }
}
