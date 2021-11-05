package com.example.kotlin_template.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component("vc")
class ViewConfig(private val mapper: ObjectMapper) {
    fun toJson(d: Any): String {
        return this.mapper.writeValueAsString(d)
    }
}
