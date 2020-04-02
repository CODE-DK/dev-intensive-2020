package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    id: String,
    from: User,
    chat: Chat,
    var text: String?,
    date: Date = Date(),
    isIncoming: Boolean = false
): BaseMessage(id, from, chat, isIncoming, date)  {

    override fun formatMessage() =
        "${from?.firstName} ${if (isIncoming) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}