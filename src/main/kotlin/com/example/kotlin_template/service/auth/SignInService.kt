package com.example.kotlin_template.service.auth

import com.example.kotlin_template.dao.User
import com.example.kotlin_template.mapper.auth.UserMapper
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignInService(private val userMapper: UserMapper, private val cryptEncoder: BCryptPasswordEncoder) {
    fun signIn(loginId: String, password: String): User {
        val userId = UUID.randomUUID().toString()
        val user = User(id = null, loginId = loginId, userId = userId, password = cryptEncoder.encode(password), roles = AuthorityUtils.createAuthorityList("user"))
        val result = userMapper.insert(user)
        println(result.toString())
        return result
    }
}