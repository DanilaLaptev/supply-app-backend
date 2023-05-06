package com.diploma.supplyapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableScheduling
class SupplyAppApplication

fun main(args: Array<String>) {
    runApplication<SupplyAppApplication>(*args)
}
