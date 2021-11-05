package com.example.kotlin_template.config

import com.example.kotlin_template.service.auth.MyUserDetailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SpringSecurityConfig(private val userDetailsService: MyUserDetailService) : WebSecurityConfigurerAdapter() {

    // パスワードのエンコードをBcryptに
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // SpringSecurityの設定
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            // ログインとサインインを許可
            .antMatchers("/auth/login", "/auth/signin").permitAll()
            // リソース系は許可
            .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
            // そのほかは認証必要
            .anyRequest().authenticated()
            .and()
            // フォームでのログインを実施
            .formLogin()
            // ログインページのURL指定
            .loginPage("/auth/login")
            // ログイン後のURL指定
            // TODO: alwaysUseについては調べる
            .defaultSuccessUrl("/service", true)
            .and()
            // ログアウト設定
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .and()
            // CSRFを実施
            .csrf()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder())
    }
}