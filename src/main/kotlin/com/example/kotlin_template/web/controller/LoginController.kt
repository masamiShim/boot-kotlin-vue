package com.example.kotlin_template.web.controller

import com.example.kotlin_template.service.auth.SignInService
import com.example.kotlin_template.web.request.SignInRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class LoginController(private val service: SignInService) {
    @GetMapping("auth/login")
    fun login(): String {
        return "auth/login"
    }

    @GetMapping("auth/signin")
    fun signIn(): String {
        return "auth/signin"
    }

    @PostMapping("auth/signin")
    fun signInPost(@RequestBody body: SignInRequest): String {
        this.service.signIn(body.loginId, body.password)
        return "auth/signin"
    }
}