package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Subject(
        val name: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val course: Course
): AbstractKPersistable<Long>()

@Repository
interface SubjectRepository: CrudRepository<Subject, Long>

