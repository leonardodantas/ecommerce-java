package com.br.servico.api.produtos.config.security;

import com.br.servico.api.produtos.config.security.services.IResouserOwnerService;
import com.br.servico.api.produtos.config.security.services.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IResouserOwnerService resouserOwnerService;

    private final String SIMPLE = "SIMPLE";

    private static final String MANAGER = "MANAGER";
    private static final String ADMIN = "ADMIN";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2").permitAll()
                .antMatchers(HttpMethod.PUT,"/v1/prices").hasRole(MANAGER)
                .antMatchers(HttpMethod.GET,"/v1/prices/product/**").hasAnyRole(ADMIN,MANAGER)
                .antMatchers(HttpMethod.POST, "/v1/products").hasRole(MANAGER)
                .antMatchers(HttpMethod.GET, "/v1/products/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/products").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/promotion/price").hasRole(MANAGER)
                .antMatchers(HttpMethod.GET, "/v1/promotion/product/**").hasAnyRole(ADMIN,MANAGER)
                .antMatchers(HttpMethod.POST, "/v1/tags").hasRole(ADMIN)
                .antMatchers(HttpMethod.GET, "/v1/tags").hasAnyRole(ADMIN,MANAGER)
                .antMatchers(HttpMethod.GET, "/v1/tags/**").hasAnyRole(ADMIN,MANAGER)
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(AuthenticationWithTokenAndFilter.of(resouserOwnerService,tokenService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/h2");
    }
}