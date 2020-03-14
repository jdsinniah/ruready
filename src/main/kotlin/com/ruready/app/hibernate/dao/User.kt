package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Entity

@Entity
class User(
        val name: String,
        val lastname: String,
        val email: String,
        val password: String,
        val certified: Boolean = false
): AbstractKPersistable<Long> ()

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findUserByEmail(email: String): Optional<User>
}
