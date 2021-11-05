package com.example.kotlin_template.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfigure :  WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // 「/resources/**」配下へのアクセスを受けた際にはクラスパス配下の「/static/**」から該当リソースを見つける
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/")
    }
}