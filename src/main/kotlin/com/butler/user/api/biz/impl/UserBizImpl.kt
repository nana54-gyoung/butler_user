package com.butler.user.api.biz.impl

import com.butler.user.api.biz.UserBiz
import com.butler.user.api.ui.vo.JoinVO
import com.butler.user.api.ui.vo.LoginVO
import com.butler.user.api.ui.vo.ResMetaVO
import com.butler.user.api.ui.vo.ResVO
import com.butler.user.common.exception.ButlerException
import com.butler.user.common.util.CBCUtil
import com.butler.user.common.util.JwtUtil
import com.butler.user.model.entity.MemberEntity
import com.butler.user.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class UserBizImpl(
    private val memberRepository : MemberRepository,
    private val passwordEncoder: CBCUtil
) : UserBiz {

    override fun join(joinVO: JoinVO): ResVO {
        return try {
            if(joinVO.checkTel() && !joinVO.password.isNullOrBlank()) {
                val joinRes = memberRepository.save(MemberEntity().apply {
                    this.id = joinVO.id
                    this.tel = joinVO.tel
                    this.password = passwordEncoder.encryptCBC(joinVO.password!!)
                    this.role = joinVO.role
                })
                ResVO(meta = ResMetaVO(200, "ok"), data = joinRes)
            } else ResVO(meta = ResMetaVO(400, "휴대폰 번호가 올바르지 않습니다."))
        }catch (e : ButlerException) {
            ResVO(meta = ResMetaVO(400, e.msg))
        }
    }

    override fun login(loginVo: LoginVO): ResVO {
        try {
            loginVo.id?.let{
                memberRepository.findById(it)?.let{ member ->
                    return if (!loginVo.password.isNullOrBlank() && loginVo.password == passwordEncoder.decryptCBC(member.password!!)) {
                        ResVO(meta = ResMetaVO(200, "ok"), data = JwtUtil.createToken(member) )
                    } else ResVO(meta = ResMetaVO(400, "비밀번호가 올바르지 않습니다."))
                } ?: throw ButlerException("M-002", "회원을 찾을 수 없습니다.")
            } ?: throw ButlerException("M-001", "휴대폰 번호를 입력해주세요.")
        } catch (e : ButlerException) {
            return ResVO(meta = ResMetaVO(400, e.msg))
        }
    }
}