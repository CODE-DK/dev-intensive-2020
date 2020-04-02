package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView(): UserView {
    val status =
        when {
            lastVisit == null -> "Еще ни разу не был"
            isOnline -> "online"
            else -> "В последний раз был ${lastVisit?.humanizeDiff()}"
        }
    val initials = Utils.toInitials(firstName, lastName)
    val nickName = Utils.transliteration("$firstName $lastName")

    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        status = status,
        initials = initials,
        nickName = nickName
    )
}



