package gt.edu.miumg.bienestar.config;

import gt.edu.miumg.bienestar.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para permitir peticiones desde Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/clientes/registrar").permitAll() // Permitir registrar cliente sin login
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permitir Swagger UI
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
