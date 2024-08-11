package com.butler.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ButlerUserApplication

fun main(args: Array<String>) {
    runApplication<ButlerUserApplication>(*args)
}
