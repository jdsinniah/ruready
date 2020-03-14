package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity

@Entity
class University(
        val name: String
): AbstractKPersistable<Long>()

@Repository
interface UniversityRepository: CrudRepository<University, Long>