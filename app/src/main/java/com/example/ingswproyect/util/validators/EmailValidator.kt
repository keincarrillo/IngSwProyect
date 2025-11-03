package com.example.ingswproyect.util.validators
object EmailValidator {

    private val baseRegex = Regex(
        pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    )

    private val allowedDomains = setOf(
        "gmail.com","hotmail.com","outlook.com","live.com",
        "yahoo.com","icloud.com","proton.me","protonmail.com",
        "aol.com","mail.com","zoho.com","yandex.com"
    )
    fun isValid(email: String, enforceAllowedDomains: Boolean = true): Boolean {
        if (!baseRegex.matches(email)) return false
        if (!enforceAllowedDomains) return true

        val domain = email.substringAfter('@').lowercase()
        return domain in allowedDomains
    }
}
