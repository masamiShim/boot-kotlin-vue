package com.example.kotlin_template.api.contoller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v1/users")
class UserApiController {
    @GetMapping("/")
    fun paginate() {

    }
}