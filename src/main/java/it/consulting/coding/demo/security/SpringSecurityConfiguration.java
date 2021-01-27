package it.consulting.coding.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("**")
                .permitAll();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfigs = new CorsConfiguration().applyPermitDefaultValues();
        corsConfigs.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfigs.addAllowedMethod(HttpMethod.GET);
        corsConfigs.addAllowedMethod(HttpMethod.POST);
        corsConfigs.addAllowedMethod(HttpMethod.PUT);
        corsConfigs.addAllowedMethod(HttpMethod.PATCH);
        corsConfigs.addAllowedMethod(HttpMethod.DELETE);
        corsConfigs.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfigs.setMaxAge(3600L);
        corsConfigs.addAllowedOrigin("*");
        corsConfigs.addAllowedHeader("Content-Type");
        corsConfigs.addAllowedHeader("Range");
        corsConfigs.addExposedHeader("Authorization");
        corsConfigs.addExposedHeader("Link");
        corsConfigs.addExposedHeader("X-Total-Count");
        corsConfigs.addExposedHeader("X-Forwarded-For");
        source.registerCorsConfiguration("/**", corsConfigs);
        return source;
    }
}
