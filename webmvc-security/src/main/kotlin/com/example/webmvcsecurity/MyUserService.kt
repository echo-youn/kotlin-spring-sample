package com.example.webmvcsecurity

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserService(
    private val myUserRepository: MyUserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = myUserRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User $username not found")
        return user
    }
}
