package calories_control.features.auth.infra.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(true))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/error/**",
                                                                "/auth/**",
                                                                "/css/**",
                                                                "/js/**",
                                                                "/images/**",
                                                                "/favicon.ico",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui/**",
                                                                "/swagger-resources/**",
                                                                "/webjars/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/auth/login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/home", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/auth/login?logout")
                                                .permitAll())
                                .build();
        }

}