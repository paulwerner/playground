package org.paulwerner.blog.security

import org.paulwerner.blog.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class SecUserDetails(private var user: User) : UserDetails {


    private val grantedAuthorities: MutableCollection<out GrantedAuthority>


    init {
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(*this.user.authorities)
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities

    override fun getUsername(): String = user.email

    override fun getPassword(): String = user.password

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

}