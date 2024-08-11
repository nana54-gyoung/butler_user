package com.butler.user.api.biz

import com.butler.user.api.ui.vo.JoinVO
import com.butler.user.api.ui.vo.LoginVO
import com.butler.user.api.ui.vo.ResVO

interface UserBiz {
    fun join(joinVO : JoinVO) : ResVO
    fun login(loginVo : LoginVO) : ResVO
}