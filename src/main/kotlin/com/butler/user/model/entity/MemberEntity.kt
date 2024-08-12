package com.butler.user.model.entity

import com.butler.user.common.converter.MemberRoleConverter
import com.butler.user.model.enums.MEMBER_ROLE
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "member")
data class MemberEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)var mbno:Int? = null) {
    @Convert(converter = MemberRoleConverter::class) var role : MEMBER_ROLE? = null
    var id : String? = null
    var tel : String? = null
    var password : String? = null
    var bmno : Int? = null
    var regdt : LocalDateTime? = LocalDateTime.now()
}
