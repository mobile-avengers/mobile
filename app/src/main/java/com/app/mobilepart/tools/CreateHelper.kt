package com.app.mobilepart.tools

class CreateHelper {
    fun checkLink(link: String): Boolean {
        val pattern = "\\b(?:https?://)?dw4\\.co/t/A/\\w+\\b".toRegex()
        val matches = pattern.findAll(link).toList()
        println(matches)
        return matches.size == 1
    }
}