package com.ruready.app.models.hibernate.entities

import javax.persistence.Entity

@Entity
class User(
        val name: String,
        val lastname: String,
        val email: String,
        val password: String,
        val certified: Boolean = false
): BaseEntity<Long>()

