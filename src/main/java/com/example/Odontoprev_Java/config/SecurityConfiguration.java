package com.example.Odontoprev_Java.config;

import com.example.Odontoprev_Java.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()
                        .requestMatchers(
                                "/actuator/prometheus",
                                "/",
                                "/usuario/api/",
                                "/paciente/api/",
                                "/procedimento/**",
                                "/auditor/api/",
                                "/clinica/register",
                                "/paciente/register"
                        ).permitAll()
                        .requestMatchers("/agendamentos/**").hasAnyRole("CLINICA", "PACIENTE")
                        .requestMatchers("/clinica/all").hasRole("AUDITOR")
                        .requestMatchers("/paciente/all").hasRole("AUDITOR")
                        .requestMatchers("/clinica/**").hasRole("CLINICA")
                        .requestMatchers("/paciente/**").hasRole("PACIENTE")
                        .requestMatchers("/auditor/**").hasRole("AUDITOR")
                        .requestMatchers("/actuator/**", "/procedimento/**", "/plano/**").hasRole("AUDITOR")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/agendamentos/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}

