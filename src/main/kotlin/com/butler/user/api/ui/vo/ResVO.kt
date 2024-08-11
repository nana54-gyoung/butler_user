package com.butler.user.api.ui.vo

data class ResVO(
    var meta : ResMetaVO? = null,
    var data : Any? = null
)

data class ResMetaVO(
    var code : Int? = null,
    var message : String? = null,
)