package com.butler.user.model.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "butler_member")
data class ButlerMemberEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)var bmno:Int? = null) {
    var id : String? = null
    var tel : String? = null
    var password : String? = null
    var regdt : LocalDateTime? = LocalDateTime.now()
}
