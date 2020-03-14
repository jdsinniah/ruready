package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Exam(
        val name: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val subject: Subject
): AbstractKPersistable<Long>()

@Repository
interface ExamRepository: CrudRepository<Exam, Long>
