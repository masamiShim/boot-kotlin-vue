package com.example.kotlin_template.dao

import com.example.kotlin_template.utils.NoArg
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@NoArg
data class User(
    val id: Long? = null,
    val userId: String,
    val loginId: String,
    private val password: String,
    val roles: MutableCollection<out GrantedAuthority>
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        // TODO: 必要な場合は
        return this.loginId
    }

    override fun isAccountNonExpired(): Boolean {
        // TODO: 必要な場合は
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        // TODO: 必要な場合は
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        // TODO: 必要な場合は
        return true
    }

    override fun isEnabled(): Boolean {
        // TODO: 必要な場合は
        return true
    }

}
