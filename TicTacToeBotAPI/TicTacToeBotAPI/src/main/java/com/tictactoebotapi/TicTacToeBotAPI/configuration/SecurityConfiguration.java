package com.tictactoebotapi.TicTacToeBotAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        registry.requestMatchers(req -> req.getMethod().equalsIgnoreCase("OPTIONS")).permitAll();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .cors(withDefaults());

        return http.build();
    }
}