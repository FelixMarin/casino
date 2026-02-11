package com.oauth.rest.security.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                    // H2 solo en dev
                    .antMatchers("/h2-console/**").permitAll()

                    // Swagger solo ADMIN
                    .antMatchers(
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/v2/api-docs",
                            "/swagger-resources/**",
                            "/webjars/**"
                    ).hasRole("ADMIN")

                    // Endpoints de usuario
                    .antMatchers(HttpMethod.POST, "/user").permitAll()   // registro
                    .antMatchers(HttpMethod.GET, "/user/me").authenticated()

                    // Cualquier otra ruta
                    .anyRequest().authenticated()

                .and()
                .headers().frameOptions().disable();
    }
}
