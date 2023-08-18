package com.tkxel.microknowledgesystem.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Log4j2
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            final String authHeader = request.getHeader("authorization");
            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                filterChain.doFilter(request , response);
            } else {
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    throw new IllegalStateException("Issue With Token Data");
                }
            }
            String tokenStrength = "secretKEYsecretKEYsecretKEYsecretKEYsecretKEYsecretKEYsecretKEY";
            final String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey(tokenStrength).parseClaimsJws(token).getBody();
            request.setAttribute("claims" , claims);
            request.setAttribute("micro" , servletRequest.getParameter("id"));
            filterChain.doFilter(request , response);
        } catch (Exception e) {
            log.error(e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
