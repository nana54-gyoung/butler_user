package com.butler.user.repository

import com.butler.user.model.entity.MemberEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: CrudRepository<MemberEntity, Int> {

    @Query("select m from MemberEntity m where m.id=:id")
    fun findById(id : String) : MemberEntity?
}