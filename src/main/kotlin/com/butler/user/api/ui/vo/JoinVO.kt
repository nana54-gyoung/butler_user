package com.butler.user.api.ui.vo

import com.butler.user.model.enums.MEMBER_ROLE

data class JoinVO(
    var id : String? = null,
    var tel : String? = null,
    var password : String? = null,
    var role : MEMBER_ROLE? = null
) {
    fun checkTel() : Boolean {
        this.tel?.let { tel ->
            val regex = "^^\\d{10,11}".toRegex()
            return regex.matches(tel)
        } ?: return false
    }
}