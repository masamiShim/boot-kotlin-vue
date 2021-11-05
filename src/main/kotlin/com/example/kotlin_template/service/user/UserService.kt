package com.example.kotlin_template.service.user

import com.example.kotlin_template.dao.User
import com.example.kotlin_template.mapper.auth.UserMapper
import org.springframework.stereotype.Service

@Service
class UserService(private val mapper: UserMapper) {
    fun paginate(current: Int, perPage: Int): List<User> {
        return this.mapper.paginate(UserMapper.PaginateFetchCondition(current, perPage))
    }
}