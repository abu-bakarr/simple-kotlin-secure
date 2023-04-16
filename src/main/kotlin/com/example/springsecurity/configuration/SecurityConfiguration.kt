package com.example.springsecurity.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize("/landing.html", permitAll)
                authorize("/admin.html", hasRole("ADMIN"))
                authorize(anyRequest, authenticated)
            }
            formLogin { }
            logout {  }
        }
        return http.build()
    }

    @Bean
    fun users(dataSource: DataSource): UserDetailsService {
        return  JdbcUserDetailsManager(dataSource)
//        val user = User.builder()
//            .username("user")
//            .password("{bcrypt}\$2a\$10\$pn2eO3z7JTqge9SI4r6tLerm3wr7UlkVDXgY.yOoEUz0REr3FK4vi")
//            .roles("USER")
//            .build()
//        val admin = User.builder()
//            .username("admin")
//            .password("{bcrypt}\$2a\$10\$pn2eO3z7JTqge9SI4r6tLerm3wr7UlkVDXgY.yOoEUz0REr3FK4vi")
//            .roles("USER", "ADMIN")
//            .build()
//        return InMemoryUserDetailsManager(user, admin)
    }
}