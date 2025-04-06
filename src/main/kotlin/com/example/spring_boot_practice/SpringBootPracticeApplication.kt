package com.example.spring_boot_practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootPracticeApplication

fun main(args: Array<String>) {
	println("DATABASE_URI: " + System.getenv("MONGODB_CONNECTION"))
	runApplication<SpringBootPracticeApplication>(*args)
}