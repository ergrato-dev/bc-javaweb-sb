package com.bootcamp.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * PRÁCTICA 01 — Implementar JwtService y JwtAuthenticationFilter
 *
 * Descomenta los pasos en orden para construir progresivamente el sistema JWT.
 */
public class JwtApp {

    // ============================================
    // PASO 2: JwtService — Generar y Validar Tokens
    //
    // - generateToken: crea un JWT firmado con HMAC-SHA256
    // - extractUsername: lee el subject del payload
    // - isValid: verifica firma, expiración y que el username coincida
    // Descomenta las siguientes líneas:
    // ============================================

    // static class JwtService {
    //
    //     // Mínimo 32 caracteres para HMAC-SHA256 (256 bits)
    //     private static final String SECRET =
    //             "dev-super-secret-key-at-least-32-characters-long";
    //     private static final long EXPIRATION_MS = 86_400_000L; // 24 horas
    //
    //     public String generateToken(UserDetails userDetails) {
    //         return Jwts.builder()
    //                 // sub = username (identificador estándar del usuario)
    //                 .subject(userDetails.getUsername())
    //                 // claim personalizado: lista de roles del usuario
    //                 .claim("roles", userDetails.getAuthorities().stream()
    //                         .map(a -> a.getAuthority())
    //                         .toList())
    //                 .issuedAt(new Date())
    //                 .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
    //                 // Firmar con HMAC-SHA256 usando la clave secreta
    //                 .signWith(getSigningKey())
    //                 .compact();
    //     }
    //
    //     public String extractUsername(String token) {
    //         return getClaims(token).getSubject();
    //     }
    //
    //     public boolean isValid(String token, UserDetails userDetails) {
    //         var username = extractUsername(token);
    //         return username.equals(userDetails.getUsername()) && !isExpired(token);
    //     }
    //
    //     private boolean isExpired(String token) {
    //         return getClaims(token).getExpiration().before(new Date());
    //     }
    //
    //     private Claims getClaims(String token) {
    //         // parseSignedClaims lanza JwtException si la firma es inválida o el token expiró
    //         return Jwts.parser()
    //                 .verifyWith(getSigningKey())
    //                 .build()
    //                 .parseSignedClaims(token)
    //                 .getPayload();
    //     }
    //
    //     private SecretKey getSigningKey() {
    //         // Decodificar la clave secreta de Base64 a bytes
    //         return Keys.hmacShaKeyFor(Decoders.BASE64.decode(
    //                 java.util.Base64.getEncoder().encodeToString(SECRET.getBytes())));
    //     }
    // }

    // ============================================
    // PASO 3: JwtAuthenticationFilter
    //
    // OncePerRequestFilter garantiza ejecución única por request,
    // incluso en forwards o redirects internos.
    // Descomenta las siguientes líneas:
    // ============================================

    // static class JwtAuthenticationFilter extends OncePerRequestFilter {
    //
    //     private final JwtService jwtService;
    //     private final UserDetailsService userDetailsService;
    //
    //     JwtAuthenticationFilter(JwtService jwtService,
    //                             UserDetailsService userDetailsService) {
    //         this.jwtService = jwtService;
    //         this.userDetailsService = userDetailsService;
    //     }
    //
    //     @Override
    //     protected void doFilterInternal(HttpServletRequest request,
    //                                     HttpServletResponse response,
    //                                     FilterChain filterChain)
    //             throws ServletException, IOException {
    //
    //         // 1. Leer el header Authorization
    //         var authHeader = request.getHeader("Authorization");
    //
    //         // 2. Si no hay token o no es Bearer → pasar al siguiente filtro
    //         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
    //             filterChain.doFilter(request, response);
    //             return;
    //         }
    //
    //         // 3. Extraer el token (quitar "Bearer " del inicio)
    //         var token = authHeader.substring(7);
    //
    //         try {
    //             var username = jwtService.extractUsername(token);
    //
    //             // 4. Solo procesar si hay username y no hay autenticación previa en el contexto
    //             if (username != null
    //                     && SecurityContextHolder.getContext().getAuthentication() == null) {
    //
    //                 var userDetails = userDetailsService.loadUserByUsername(username);
    //
    //                 // 5. Validar el token
    //                 if (jwtService.isValid(token, userDetails)) {
    //                     // 6. Crear el objeto de autenticación y guardarlo en el contexto
    //                     var authToken = new UsernamePasswordAuthenticationToken(
    //                             userDetails, null, userDetails.getAuthorities());
    //                     authToken.setDetails(
    //                             new WebAuthenticationDetailsSource().buildDetails(request));
    //                     SecurityContextHolder.getContext().setAuthentication(authToken);
    //                 }
    //             }
    //         } catch (JwtException ex) {
    //             // Token inválido, expirado o malformado → limpiar contexto y continuar
    //             // El filtro de autorización devolverá 401
    //             SecurityContextHolder.clearContext();
    //         }
    //
    //         filterChain.doFilter(request, response);
    //     }
    // }

    // ============================================
    // PASO 4: Recordatorio — SecurityFilterChain con JWT
    //
    // En la clase SecurityConfig real, el FilterChain debe configurarse así.
    // Para ver el ejemplo completo, revisa el proyecto starter de esta semana.
    // ============================================

    // Clave es agregar estas dos líneas al SecurityFilterChain:
    // .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

    public static void main(String[] args) {
        System.out.println("=== Práctica 01: JWT ===");
        System.out.println("Descomenta PASO 2, luego PASO 3.");
        System.out.println("Crea una clase @SpringBootApplication separada para ejecutar.");
        System.out.println();
        System.out.println("Decodifica un JWT en https://jwt.io para ver sus claims.");
    }
}
