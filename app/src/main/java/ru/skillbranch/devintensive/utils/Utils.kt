package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val list = fullName?.split(" ")
        val firstName = list?.getOrNull(0)
        val lastName = list?.getOrNull(1)
        return firstName.takeIf { !it.isNullOrBlank() } to lastName.takeIf { !it.isNullOrBlank() }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val pair = payload.split(" ")
        var builder = ""
        var buffer = ""
        val iterator = pair.iterator()
        while (iterator.hasNext()) {
            val toCharArray = iterator.next().toCharArray()
            for (symbol in toCharArray) {
                builder += when (symbol.toLowerCase()) {
                    'а' -> 'a'
                    'б' -> 'b'
                    'в' -> 'v'
                    'г' -> 'g'
                    'д' -> 'd'
                    'е' -> 'e'
                    'ё' -> 'e'
                    'ж' -> "zh"
                    'з' -> 'z'
                    'и' -> 'i'
                    'й' -> 'i'
                    'к' -> 'k'
                    'л' -> 'l'
                    'м' -> 'm'
                    'н' -> 'n'
                    'о' -> 'o'
                    'п' -> 'p'
                    'р' -> 'r'
                    'с' -> 's'
                    'т' -> 't'
                    'у' -> 'u'
                    'ф' -> 'f'
                    'х' -> 'h'
                    'ц' -> 'c'
                    'ч' -> "ch"
                    'ш' -> "sh"
                    'щ' -> "sh"
                    'ъ' -> ""
                    'ы' -> 'i'
                    'ь' -> ""
                    'э' -> 'e'
                    'ю' -> "yu"
                    'я' -> "ya"
                    else -> symbol
                }
            }
            buffer += if (toCharArray[0].isUpperCase()) builder[0].toUpperCase() + builder.substring(1) else builder
            buffer += if (iterator.hasNext()) divider else ""
            builder = ""
        }
        return buffer
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val x = if (firstName.isNullOrBlank()) "" else firstName.substring(0, 1)
            .toUpperCase(Locale.ROOT)
        val y =
            if (lastName.isNullOrBlank()) "" else lastName.substring(0, 1).toUpperCase(Locale.ROOT)
        return if ("$x$y".isNotBlank()) "$x$y" else null
    }
}