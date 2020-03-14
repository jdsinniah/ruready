package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
class Question(
        val question: String,
        @OneToMany(
                mappedBy = "question",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val answers: List<Answer>,
        @ManyToOne(fetch = FetchType.LAZY)
        val exam: Exam
): AbstractKPersistable<Long>()

@Repository
interface QuestionRepository: CrudRepository<Question, Long>
