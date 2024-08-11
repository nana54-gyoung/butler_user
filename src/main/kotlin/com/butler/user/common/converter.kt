package com.butler.user.common

import com.butler.user.common.exception.ButlerException
import com.butler.user.model.enums.MEMBER_ROLE
import jakarta.persistence.AttributeConverter

class MemberRoleConverter  : AttributeConverter<MEMBER_ROLE, String> {
    override fun convertToDatabaseColumn(attribute: MEMBER_ROLE?): String? {
        return attribute?.role
    }

    override fun convertToEntityAttribute(dbData: String?): MEMBER_ROLE {
        return try{
            MEMBER_ROLE.values().filter { it.role == dbData }.first()
        }catch (e: Exception) {
            throw ButlerException("G-001", "해당 권한이 없습니다.")
        }
    }
}