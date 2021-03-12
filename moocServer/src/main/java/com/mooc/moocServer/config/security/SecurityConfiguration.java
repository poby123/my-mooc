package com.mooc.moocServer.config.security;

import com.mooc.moocServer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 기본 설정은 비인증시 로그인 화면으로 리다이렉트이지만, rest api 이므로 disable 로 설정한다.
                .httpBasic().disable()

                // rest api 이므로 csrf 보안이 필요하지 않으므로 disable 로 설정한다.
                .csrf().disable()

                // 세션설정. jwt token 으로 인증하므로 세션은 필요하지 않으므로 생성하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                // 다음 requests 에 대한 사용권한을 체크한다.
                .authorizeRequests()

                // 모두에게 혀용할 주소 설정
                .antMatchers("/signin", "/signup").permitAll()

                // 그 밖에 요청은 인증된 회원만 접근하도록 설정.
                .anyRequest().hasRole("USER")

                .and()

                // jwt token 필터를 id/password 인증 필터 전에 넣는다.
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                );
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(memberService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    }
}
