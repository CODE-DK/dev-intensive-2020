package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    val str = this.substring(0, length).trim()
    return when (str.length) {
        in length - 1..length -> "$str..."
        else -> str
    }
}

fun String.stripHtml(): String {
    return this
}

