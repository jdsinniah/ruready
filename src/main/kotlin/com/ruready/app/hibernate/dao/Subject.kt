package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
class Subject(
        val name: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val course: Course,
        @OneToMany(
                mappedBy = "subject",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val exams: List<Exam>?
): AbstractKPersistable<Long>()

@Repository
interface SubjectRepository: CrudRepository<Subject, Long>

