package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class SignedUpExam(
        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,
        @ManyToOne(fetch = FetchType.LAZY)
        val exam: Exam,
        val signedUpAt: Date,
        val startedAt: Date? = null,
        val finishedAt: Date? = null
): AbstractKPersistable<Long>()

@Repository
interface SignedUpExamRepository<PK>: CrudRepository<SignedUpExam, PK>
