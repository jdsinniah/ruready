package com.ruready.app.hibernate.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity

@Entity
class Course(
        val name: String
): AbstractKPersistable<Long>()

@Repository
interface CourseRepository: CrudRepository<Course, Long>
