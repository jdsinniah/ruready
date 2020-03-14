package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class User(
        val name: String,
        val lastname: String,
        val email: String,
        val password: String,
        val certified: Boolean = false,
        @ManyToOne
        @JoinColumn(name = "university_id", nullable = true)
        val university: University?,
        @ManyToOne
        @JoinColumn(name = "course_id", nullable = true)
        val course: Course?
): AbstractKPersistable<Long> ()

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findUserByEmail(email: String): Optional<User>
}
