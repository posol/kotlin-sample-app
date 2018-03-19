package ru.posol.sample.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import org.springframework.context.annotation.Bean

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import ru.posol.sample.security.CustomAccessDeniedHandlerImpl
import ru.posol.sample.security.Http401AuthenticationEntryPoint

/**
 * Configuration class for configurating security
 */
@EnableWebSecurity
@Configuration
class RestSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.inMemoryAuthentication()
                ?.withUser("user")?.password("{noop}user")?.roles("USER")?.and()
                ?.withUser("admin")?.password("{noop}admin")?.roles("USER","ADMIN")?.and()
                ?.withUser("other")?.password("{noop}other")?.roles("OTHER")
    }

    override fun configure(http: HttpSecurity?) {

        http
            ?.csrf()?.disable()
            ?.authorizeRequests()
                ?.antMatchers( "/api/*")?.hasRole("ADMIN")
                ?.antMatchers( "/api/*")?.hasRole("USER")
            ?.and()
            ?.exceptionHandling()?.accessDeniedHandler(CustomAccessDeniedHandlerImpl())
            ?.and()
            ?.httpBasic()?.authenticationEntryPoint(Http401AuthenticationEntryPoint())
   }


}

