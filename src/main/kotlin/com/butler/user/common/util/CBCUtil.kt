package com.butler.user.common.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class CBCUtil {

    @Value("\${butler.key}") private lateinit var secretKey: String
    @Value("\${butler.iv}") private lateinit var secretIv: String
    private val encorder = Base64.getEncoder()
    private val decorder = Base64.getDecoder()

    fun encryptCBC(pw: String): String{
        try {
            val ivSpec = IvParameterSpec(secretIv.toByteArray())
            val keySpec = SecretKeySpec(secretKey.toByteArray(), "AES")    /// 키
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")     //싸이퍼
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)       // 암호화/복호화 모드
            val crypted = cipher.doFinal(pw.toByteArray(Charsets.UTF_8))
            return encorder.encodeToString(crypted)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    fun decryptCBC(pw: String): String {
        val decodedByte: ByteArray = decorder.decode(pw.toByteArray(Charsets.UTF_8))
        val ivSpec = IvParameterSpec(secretIv.toByteArray())
        val keySpec = SecretKeySpec(secretKey.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val output = cipher.doFinal(decodedByte)
        return String(output)
    }
}