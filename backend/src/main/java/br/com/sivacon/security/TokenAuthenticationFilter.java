package br.com.sivacon.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.sivacon.service.AuthService;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;

    public TokenAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            var principal = authService.authenticate(header.substring(7).trim());
            if (principal != null) {
                var authentication = new UsernamePasswordAuthenticationToken(principal, null,
                        java.util.List.of(new SimpleGrantedAuthority("ROLE_" + principal.role())));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
