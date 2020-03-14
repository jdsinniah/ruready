package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class University(
        val name: String,
        @OneToMany(
                mappedBy = "university",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val courses: List<Course>
): AbstractKPersistable<Long>()

@Repository
interface UniversityRepository: CrudRepository<University, Long>
