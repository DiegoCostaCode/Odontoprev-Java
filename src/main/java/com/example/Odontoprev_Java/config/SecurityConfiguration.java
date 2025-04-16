package com.example.Odontoprev_Java.config;

import com.example.Odontoprev_Java.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //Acessos para os arquivos estáticos
                        .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()
                        //Acessos para o H2
                        .requestMatchers("/h2-console/**").permitAll()
                        //Acessos para registros
                        .requestMatchers(
                                "/",
                                "/usuario/api/",
                                "/auditor/api/",
                                "clinica/register",
                                "paciente/register"
                        ).permitAll()
                        .requestMatchers("/clinica/**").hasRole("CLINICA")
                        .requestMatchers("/paciente/**").hasRole("PACIENTE")
                        .requestMatchers("/auditor/**").hasRole("AUDITOR")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

