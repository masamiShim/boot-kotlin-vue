package com.example.kotlin_template.service.auth

import com.example.kotlin_template.mapper.auth.UserMapper
import com.example.kotlin_template.utils.Logger
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetailService(private val userMapper: UserMapper)
    : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        Logger.debug(username ?: "username none")
        return this.userMapper.findByLoginId(username ?: "")
            ?: throw UsernameNotFoundException("user not found. [userName: $username]")
    }
}