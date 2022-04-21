package jrvsoft.ppmtool.security;

import com.google.gson.Gson;
import jrvsoft.ppmtool.exception.InvalidLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        log.error("error here");
//        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
//        String jsonLoginResponse = new Gson().toJson(loginResponse);
//
//
//        httpServletResponse.setContentType("application/json");
//        httpServletResponse.setStatus(401);
//        httpServletResponse.getWriter().print(jsonLoginResponse);


    }
}