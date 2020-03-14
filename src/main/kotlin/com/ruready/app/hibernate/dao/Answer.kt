package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class Answer(
        val answer: String,
        val isCorrect: Boolean,
        @ManyToOne(fetch = FetchType.LAZY)
        val question: Question
): AbstractKPersistable<Long>()

@Repository
interface AnswerRepository: CrudRepository<Answer, Long>