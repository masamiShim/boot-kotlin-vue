package com.example.kotlin_template.web.controller

import com.example.kotlin_template.service.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class DashBordController(private val service: UserService) {
    @GetMapping("/service/dashboard")
    fun service(@RequestParam("current") current: Int?, @RequestParam("perPage") perPage: Int?): ModelAndView {
        val c = current ?: 1
        val p = perPage ?: 10
        val result = this.service.paginate(c, p)
        return ModelAndView(
            "service/dashboard",
            mapOf(
                "current" to c,
                "perPage" to p,
                "users" to result.data,
                "total" to result.total
            )
        )
    }
}