package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
class User(
        val name: String,
        val lastname: String,
        val email: String,
        var password: String,
        val certified: Boolean = false,
        @ManyToOne
        @JoinColumn(name = "university_id", nullable = true)
        val university: University? = null,
        @ManyToOne
        @JoinColumn(name = "course_id", nullable = true)
        val course: Course? = null,
        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val signedUpExams: List<SignedUpExam>? = null
): AbstractKPersistable<Long> ()

@Repository
interface UserRepository<PK>: CrudRepository<User, PK> {
        fun findUserById(id: PK): User?
        fun findUserByEmail(email: String): User?
}
