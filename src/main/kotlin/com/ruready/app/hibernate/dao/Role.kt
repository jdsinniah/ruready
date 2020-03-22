package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Repository
import javax.persistence.Entity

@Entity
class Role(private val name: String):
        AbstractKPersistable<Long>(),
        GrantedAuthority {

    override fun getAuthority(): String {
        return this.name
    }

}

@Repository
interface RoleRepository<PK>: CrudRepository<Role, PK>