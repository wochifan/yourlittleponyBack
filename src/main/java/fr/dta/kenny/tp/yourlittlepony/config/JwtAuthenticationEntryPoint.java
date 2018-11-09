package fr.dta.kenny.tp.yourlittlepony.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
   "Sorry, You're not authorized to access this resource.");
}

	
}
