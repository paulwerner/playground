package org.paulwerner.blog.models

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {

    ADMIN,
    USER;

    override fun getAuthority(): String = name

}
