package com.diginamic.digihello.config;

import com.diginamic.digihello.repository.UserAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /*@Bean
    public UserDetailsService userDeaUserDetailsService() {
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build());
        userDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build());
        return userDetailsManager;
    }*/

    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        return username -> userAccountRepository.findByUsername(username).asUser();
    }

    @Bean
    SecurityFilterChain configSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("/login").permitAll()
                .requestMatchers("/", "/index").authenticated()
                .requestMatchers(HttpMethod.GET, "villes/**", "/listeVilles").authenticated()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().denyAll()
        ).httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}