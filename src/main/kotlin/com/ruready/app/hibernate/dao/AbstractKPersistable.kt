package com.ruready.app.hibernate.dao

import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractKPersistable<PK : Serializable?>(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: PK? = null
) {

    fun isNew(): Boolean {
        return null == this.id
    }

    override fun toString(): String {
        return String.format("Entity of type %s with id: %s", this.javaClass.name, this.id)
    }

    override fun equals(other: Any?): Boolean {
        if (null == other) {
            return false
        }
        if (this === other) {
            return true
        }
        if (javaClass != ProxyUtils.getUserClass(other)) {
            return false
        }
        val that = other as AbstractKPersistable<*>
        return if (null == this.id) false else this.id == that.id
    }

    override fun hashCode(): Int {
        var hashCode = 17
        hashCode += if (null == this.id) 0 else this.id.hashCode() * 31
        return hashCode
    }

    companion object {
        private const val serialVersionUID = -5554308939380869754L
    }
}
