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
@EnableMethodSecurity(prePostEnabled = true) // aktiviert @PreAuthorize auf Methoden-Ebene
@PropertySource(
        factory = IdentityServicesPropertySourceFactory.class,
        ignoreResourceNotFound = true,
        value = {""}
)
public class SecurityConfiguration {

    /**
     * Der XsuaaTokenAuthorizationConverter wird von der SAP-Autoconfiguration
     * (HybridAuthorizationAutoConfiguration) automatisch als Bean registriert.
     * Er entfernt den XSUAA App-Identifier aus den Scope-Namen, sodass
     * hasAuthority("Read") statt hasAuthority("myapp!t1234.Read") funktioniert.
     */
    @Autowired
    private Converter<Jwt, AbstractAuthenticationToken> authConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Kein Session-State – wir sind ein stateless OAuth2 Resource Server
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // CSRF deaktivieren (nicht nötig bei stateless JWT-APIs)
            .csrf(csrf -> csrf.disable())

            // ---- Endpunkte absichern ----
            .authorizeHttpRequests(authz -> authz

                // Nur Nutzer mit der Rolle "admin" oder "editor" dürfen schreiben/löschen
                .requestMatchers(HttpMethod.POST,   "/products/**").hasAnyAuthority("admin")
                .requestMatchers(HttpMethod.PUT,   "/products/**").hasAnyAuthority("admin")
                .requestMatchers(HttpMethod.DELETE,   "/products/**").hasAnyAuthority("admin")

                .requestMatchers(HttpMethod.POST,   "/versions/**").hasAnyAuthority("admin")
                .requestMatchers(HttpMethod.PUT,   "/versions/**").hasAnyAuthority("admin")
                .requestMatchers(HttpMethod.DELETE,   "/versions/**").hasAnyAuthority("admin")

                .requestMatchers(HttpMethod.POST,   "/artifacts/**").hasAnyAuthority("admin")
                .requestMatchers(HttpMethod.PUT,   "/artifacts/**").hasAnyAuthority("admin")
                .requestMatchers(HttpMethod.DELETE,   "/artifacts/**").hasAnyAuthority("admin")

                // Alle anderen (authentifizierten) Nutzer dürfen GET
                .requestMatchers(HttpMethod.GET,    "/**").authenticated()

                // Alles andere ablehnen – WICHTIG: nie vergessen!
                .anyRequest().denyAll()
            )

            // OAuth2 Resource Server mit JWT + SAP XSUAA Converter
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(authConverter)
                )
            );

        return http.build();
    }
}
