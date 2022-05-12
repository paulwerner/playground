package org.paulwerner.blog.config

import org.paulwerner.blog.security.JwtTokenFilterConfigurer
import org.paulwerner.blog.security.JwtTokenProvider
import org.paulwerner.blog.security.MongoUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(private val jwtTokenProvider: JwtTokenProvider) : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var mongoUserDetailsService: MongoUserDetailsService


    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/login").permitAll()
                .antMatchers("/api/users/signup").permitAll()
                .antMatchers(HttpMethod.POST).authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/login")
                .and()
                .apply(JwtTokenFilterConfigurer(jwtTokenProvider))
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(mongoUserDetailsService)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager =
            super.authenticationManagerBean()

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}
