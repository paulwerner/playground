package org.paulwerner.blog.config

import org.paulwerner.blog.security.SecUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(private var userDetailsService: SecUserDetailsService) : WebSecurityConfigurerAdapter() {


    @Autowired
    @Throws(Exception::class)
    fun configAuthBuilder(builder: AuthenticationManagerBuilder) {
        builder.userDetailsService<SecUserDetailsService>(userDetailsService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login*", "*/public/**").permitAll()
            .antMatchers("/v1/**").authenticated()
            .and()
            .httpBasic()
    }


}