package project.personal.personalstoremanagementproject.confiugation.Jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import project.personal.personalstoremanagementproject.services.JwtService;
import project.personal.personalstoremanagementproject.services.UserDetailService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * JwtAuthFilter
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailService userDetailService;

    private final String[] URI = {
            "/api/v1/RegisterScreen",
            "/api/v1/LoginScreen",
            "/api/v1/ForgotPasswordScreen",
            "/ws",
            "api/v1/VerifyInformation",
    };

    public JwtAuthFilter(JwtService jwtService, UserDetailService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    /**
     * Do filter internal
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        // Skip JWT validation for public endpoints
        String uri = request.getRequestURI();
        if (Arrays.asList(URI).contains(uri)) {
            filterChain.doFilter(request, response);
            logger.info("Skipping JWT validation for public endpoint: " + uri);
            return;
        }

        // Check if there is a token in the request
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authorizationHeader.substring(7);
        username = jwtService.extractUserName(jwtToken);

        // Authenticate user if not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailService.loadUserByUsername(username);

            // Validate token
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                // Get the role from JWT token
                String role = jwtService.extractRole(jwtToken);
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("User authenticated: " + username);
            }
        }
        // Continue filter chain after processing
        filterChain.doFilter(request, response);
    }


}
