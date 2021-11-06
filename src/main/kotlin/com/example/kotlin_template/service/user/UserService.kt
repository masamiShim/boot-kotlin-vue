package com.example.kotlin_template.service.user

import com.example.kotlin_template.dao.User
import com.example.kotlin_template.dao.pagination.PaginateResult
import com.example.kotlin_template.mapper.auth.UserMapper
import org.springframework.stereotype.Service

@Service
class UserService(private val mapper: UserMapper) {
    fun paginate(current: Int, perPage: Int): PaginateResult<User> {
        val data =  this.mapper.paginate(UserMapper.PaginateFetchCondition(current, perPage))
        val total = this.mapper.total(UserMapper.PaginateFetchCondition(current, perPage))
        return PaginateResult(data= data, total = total)
    }
}