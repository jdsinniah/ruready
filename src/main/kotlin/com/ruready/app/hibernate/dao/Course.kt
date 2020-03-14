package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Entity
class Course(
        val name: String,

        @ManyToOne(fetch = FetchType.LAZY)
        val university: University,

        @OneToMany(
                mappedBy = "course",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val subjects: List<Subject>
): AbstractKPersistable<Long>()

@Repository
interface CourseRepository<PK>: CrudRepository<Course, PK>
