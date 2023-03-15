package com.example.coffee.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity

public class CoffeeSecurityConfig {

    @Resource
    private UserDetailsService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] permitRes = {"/", "/image/**",
                "/styles/**", "/fonts/**", "/font-awesome-4.7.0/**", "/images_cafe/**",
                "/registration", "/js/**"};
        http.
                authorizeHttpRequests()
                .requestMatchers(permitRes).permitAll()
                .anyRequest().fullyAuthenticated();
        http.
                formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll();
        http.
                logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                              .logoutSuccessUrl("/login?logout")
                .permitAll();
        http.userDetailsService(userService);
        http.securityContext().securityContextRepository(
                new DelegatingSecurityContextRepository(
                        new RequestAttributeSecurityContextRepository(),
                        new HttpSessionSecurityContextRepository())).requireExplicitSave(true);

        return http.build();
    }


}