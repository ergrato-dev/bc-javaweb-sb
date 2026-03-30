package com.bootcamp.taskapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro JWT que se ejecuta UNA vez por request (OncePerRequestFilter).
 *
 * Flujo:
 * 1. Extrae el token del header: Authorization: Bearer <token>
 * 2. Valida el token con JwtService
 * 3. Carga el usuario desde la BD con UserDetailsService
 * 4. Establece la autenticación en el SecurityContext
 *
 * Si el token es inválido o no existe, el filtro pasa al siguiente sin error —
 * Spring Security rechazará el request en la siguiente etapa si el endpoint
 * requiere autenticación.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    // ============================================
    // TODO: Implementar el filtro JWT
    // ============================================
    // Pasos:
    // 1. Obtener el header "Authorization" del request
    // String authHeader = request.getHeader("Authorization");
    //
    // 2. Si no existe o no empieza con "Bearer ", llamar filterChain.doFilter() y
    // return
    //
    // 3. Extraer el token: String token = authHeader.substring(7);
    //
    // 4. Extraer el username del token: jwtService.extractUsername(token)
    // (puede lanzar JwtException si el token es inválido — manejar con try-catch)
    //
    // 5. Si username != null Y SecurityContext no tiene autenticación:
    // a. Cargar el usuario: userDetailsService.loadUserByUsername(username)
    // b. Validar el token: jwtService.isTokenValid(token, userDetails)
    // c. Si válido, crear UsernamePasswordAuthenticationToken y establecer en
    // SecurityContext:
    // var authToken = new UsernamePasswordAuthenticationToken(
    // userDetails, null, userDetails.getAuthorities());
    // authToken.setDetails(new
    // WebAuthenticationDetailsSource().buildDetails(request));
    // SecurityContextHolder.getContext().setAuthentication(authToken);
    //
    // 6. Siempre llamar filterChain.doFilter(request, response) al final

    filterChain.doFilter(request, response);
  }
}
