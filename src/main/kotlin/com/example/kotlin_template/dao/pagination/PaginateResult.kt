package com.example.kotlin_template.dao.pagination

data class PaginateResult<T> (val data: List<T>, val total: Int)
