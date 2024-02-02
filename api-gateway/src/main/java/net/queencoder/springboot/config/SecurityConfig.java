package net.queencoder.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
     @Bean
     public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity
        .csrf(csrf -> csrf.disable())
        .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**")
        .permitAll()
        .anyExchange()
        .authenticated())
        .oauth2ResourceServer(OAuth2ResourceServerSpec::jwt);

        return serverHttpSecurity.build();
     }

     @Bean
      public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:8181/realms/spring-boot-microservices-realm");
      }
}
