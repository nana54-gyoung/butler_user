package com.butler.user.repository

import com.butler.user.model.entity.ButlerMemberEntity
import com.butler.user.model.entity.MemberEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ButlerMemberRepository: CrudRepository<ButlerMemberEntity, Int> {
}