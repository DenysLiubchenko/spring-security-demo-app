package com.demo.demoapp.config;

import com.demo.demoapp.constatnts.SecurityConstants;
import com.demo.demoapp.filter.CsrfCookieFilter;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        return http.sessionManagement(
                sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setMaxAge(3600L);
                corsConfiguration.setExposedHeaders(List.of(SecurityConstants.JWT_HEADER));
                return corsConfiguration;
            }))
            .csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .ignoringRequestMatchers(
                    "/contact",
                    "/register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAt(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                    .requestMatchers(
                        HttpMethod.GET,
                        "/myAccount"
                    ).hasRole("ADMIN")
                    .requestMatchers(
                        HttpMethod.GET,
                        "/myLoans"
                    ).authenticated()
                    .requestMatchers(
                        HttpMethod.GET,
                        "/myBalance",
                        "/myCards"
                    ).hasAnyRole("USER", "ADMIN")
                    .requestMatchers(
                        HttpMethod.POST,
                        "/contact"
                    ).authenticated()
                    .requestMatchers(
                        HttpMethod.GET,
                        "/notices",
                        "/user"
                    ).permitAll()
                    .requestMatchers(
                        HttpMethod.POST,
                        "/contact",
                        "/register"
                    ).permitAll())
            .oauth2ResourceServer(rsc -> rsc.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(converter)))
        .build();
    }
}
