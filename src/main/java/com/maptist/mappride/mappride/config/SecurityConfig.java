package com.maptist.mappride.mappride.config;

import com.maptist.mappride.mappride.config.jwt.JwtAuthFilter;
import com.maptist.mappride.mappride.config.jwt.JwtExceptionFilter;
import com.maptist.mappride.mappride.config.oauth2.CustomOAuth2UserService;
import com.maptist.mappride.mappride.config.oauth2.handler.MyAuthenticationFailureHandler;
import com.maptist.mappride.mappride.config.oauth2.handler.MyAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyAuthenticationSuccessHandler oAuth2LoginSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthFilter jwtAuthFilter;
    private final MyAuthenticationFailureHandler oAuth2LoginFailureHandler;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors((cors) -> cors
                        .configurationSource(myWebsiteConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((policy) -> policy
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/**").permitAll() //모든 경로 인증 불필요
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico").permitAll()
                        .requestMatchers("/mypage/**").hasAnyRole("USER", "MANAGER")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .failureHandler(oAuth2LoginFailureHandler)
                        .successHandler(oAuth2LoginSuccessHandler)
                                .userInfoEndpoint(endPoint -> endPoint
                                        .userService(customOAuth2UserService))
                );

        return http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthFilter.class)
                .build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource myWebsiteConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000", "https://*.yourdomain.com")); // 와일드카드 패턴 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Credentials 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
