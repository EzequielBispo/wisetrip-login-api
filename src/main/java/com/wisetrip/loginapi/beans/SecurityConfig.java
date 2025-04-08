package com.wisetrip.loginapi.beans;

import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.wisetrip.loginapi.model.Usuario;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/usuarios").permitAll()

                        .anyRequest().authenticated())
                .httpBasic(org.springframework.security.config.Customizer.withDefaults())
                .formLogin(org.springframework.security.config.Customizer.withDefaults());

        return http.build();
    }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.builder()
			.username("admin")
			.password("{bcrypt}$2a$10$dDAtuBo5qpuKMPM0hb1M7ONUMvlB6lcknR1.5GD34r5tQF3EsmP/a")
			.roles("USER", "ADMIN")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
