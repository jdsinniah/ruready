package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
class Exam(
        val name: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val subject: Subject,
        @OneToMany(
                mappedBy = "exam",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val signedUpExams: List<SignedUpExam>?
): AbstractKPersistable<Long>()

@Repository
interface ExamRepository: CrudRepository<Exam, Long>
