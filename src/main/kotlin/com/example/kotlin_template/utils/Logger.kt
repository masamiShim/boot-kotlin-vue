package com.example.kotlin_template.utils

import com.example.kotlin_template.KotlinTemplateApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Logger {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(KotlinTemplateApplication::class.java)

        fun trace(message: String) {
            this.logger.trace(message)
        }

        fun debug(message: String) {
            this.logger.debug(message)
        }
        fun info(message: String) {
            this.logger.info(message)
        }
    }

}