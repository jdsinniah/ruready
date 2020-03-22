package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
class User(
        val name: String,
        val lastname: String,
        val email: String,
        private var password: String,
        private var enabled: Boolean,
        @ManyToMany
        @JoinTable(name = "user_role",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")])
        val roles: Set<Role>,
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
): UserDetails, AbstractKPersistable<Long> () {

        override fun getAuthorities(): Set<Role> {
                return this.roles
        }

        override fun isEnabled(): Boolean {
                return this.enabled
        }

        override fun getUsername(): String {
                return this.email
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun getPassword(): String {
                return this.password
        }

        fun setPassword(password: String) {
                this.password = password
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }
}

@Repository
interface UserRepository<PK>: CrudRepository<User, PK> {
        fun findUserById(id: PK): User?
        fun findUserByEmail(email: String): User?
}
