package com.example.kotlin_template.web.controller

import com.example.kotlin_template.service.user.UserService
import com.example.kotlin_template.utils.Logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class DashBordController(private val service: UserService) {
    @GetMapping("/service")
    fun service(@RequestParam("current") current: Int?, @RequestParam("perPage") perPage: Int?): ModelAndView {
        val c = current ?: 1
        val p = perPage ?: 10
        val users = this.service.paginate(c, p)
        val mav = ModelAndView()
        mav.addObject("current", c)
        mav.addObject("perPage", p)
        mav.addObject("users", users)
        mav.viewName = "service/index"
        Logger.info(users.toString())
        return mav
    }
}