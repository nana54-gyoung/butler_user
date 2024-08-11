package com.butler.user.api.ui.act

import com.butler.user.api.biz.UserBiz
import com.butler.user.api.ui.vo.JoinVO
import com.butler.user.api.ui.vo.LoginVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserAction (
    private val userBiz : UserBiz
) {
    @PostMapping("/join")
    fun join(@RequestBody joinVO : JoinVO) = userBiz.join(joinVO)

    @PostMapping("/login")
    fun login(@RequestBody loginVO: LoginVO) = userBiz.login(loginVO)
}