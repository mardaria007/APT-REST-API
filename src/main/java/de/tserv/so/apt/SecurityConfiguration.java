package de.tserv.so.apt;

import com.sap.cloud.security.spring.config.IdentityServicesPropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@PropertySource(
        factory = IdentityServicesPropertySourceFactory.class,
        ignoreResourceNotFound = true,
        value = {""}
)
public class SecurityConfiguration {
    @Autowired
    private Converter<Jwt, AbstractAuthenticationToken> authConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST,   "/products/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.PUT,   "/products/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE,   "/products/**").hasAuthority("admin")

                .requestMatchers(HttpMethod.POST,   "/versions/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.PUT,   "/versions/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE,   "/versions/**").hasAuthority("admin")

                .requestMatchers(HttpMethod.POST,   "/artifacts/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.PUT,   "/artifacts/**").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE,   "/artifacts/**").hasAuthority("admin")

                .requestMatchers(HttpMethod.GET,    "/**").authenticated()
                .requestMatchers(HttpMethod.OPTIONS,    "/**").authenticated()
                .anyRequest().denyAll()
            )

            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(authConverter)
                )
            );

        return http.build();
    }
}
