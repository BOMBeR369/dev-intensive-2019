package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
) {
    companion object Factory {
        var lastId = -1
        fun makeUser(fullName: String): User {
            ++lastId
            val (f, l) = Utils.parseFullName(fullName)
            return User("$lastId", f, l, null)
        }
    }

    data class Builder(
        var id : String = "${++Factory.lastId}",
        var firstName : String? = null,
        var lastName : String? = null,
        var avatar : String? = null,
        var rating : Int = 0,
        var respect : Int = 0,
        var lastVisit : Date? = Date(),
        var isOnline : Boolean = false
    ) {
        fun id(id: Int) = apply { this.id = "$id" }
        fun id(id: String) = apply { this.id = id }
        fun firstName(fn: String) = apply { this.firstName = fn }
        fun lastName(ln: String) = apply { this.lastName = ln }
        fun avatar(avatar: String) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }
        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }
}