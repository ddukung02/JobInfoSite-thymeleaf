package com.example.jobInfoSite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable);
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/index", "/css/**", "/images/**", "/account/**", "/api/**", "/docs/**", "/support/**").permitAll()
                        .requestMatchers("/board/list", "/board/view/**").permitAll()  // 게시판 읽기 허용
                        .requestMatchers("/board/form", "/board/edit/**", "/board/delete/**").authenticated()  // 게시판 쓰기 및 수정/삭제는 인증된 사용자만
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/account/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username,password,enabled from user where username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name FROM user_role ur INNER JOIN user u ON ur.user_id = u.id INNER JOIN role r ON ur.role_id = r.id WHERE u.username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
