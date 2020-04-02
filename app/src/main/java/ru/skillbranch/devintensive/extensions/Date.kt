package ru.skillbranch.devintensive.extensions

import java.lang.Character.getNumericValue
import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var delta = date.time - this.time
    if (delta > 0) {
        return when (delta) {
            in 0 * SECOND..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE -> if (lastIndexOf(delta / MINUTE) < 5) "${delta / MINUTE} минуты назад" else "${delta / MINUTE} минут назад"
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> if (lastIndexOf(delta / HOUR) < 5) "${delta / HOUR} часа назад" else "${delta / HOUR} часов назад"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> if (lastIndexOf(delta / DAY) < 5) "${delta / DAY} дня назад" else "${delta / DAY} дней назад"
            else -> "более года назад"
        }
    }
    delta *= -1
    return when (delta) {
        in 0 * SECOND..1 * SECOND -> "только что"
        in 1 * SECOND..45 * SECOND -> "через несколько секунд"
        in 45 * SECOND..75 * SECOND -> "через минуту"
        in 75 * SECOND..45 * MINUTE -> if (lastIndexOf(delta / MINUTE) < 5) "через ${delta / MINUTE} минуты" else "через ${delta / MINUTE} минут"
        in 45 * MINUTE..75 * MINUTE -> "через час"
        in 75 * MINUTE..22 * HOUR -> if (lastIndexOf(delta / HOUR) < 5) "через ${delta / HOUR} часа" else "через ${delta / HOUR} часов"
        in 22 * HOUR..26 * HOUR -> "через день"
        in 26 * HOUR..360 * DAY -> if (lastIndexOf(delta / DAY) < 5) "через ${delta / DAY} дня" else "через ${delta / DAY} дней"
        else -> "более чем через год"
    }
}

    private fun lastIndexOf(delta: Long): Int {
        val s = delta.toString()
        return getNumericValue(s[s.length - 1])
    }

    fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
        var time = this.time

        time += when (units) {
            TimeUnits.SECOND -> value * SECOND
            TimeUnits.MINUTE -> value * MINUTE
            TimeUnits.HOUR -> value * HOUR
            TimeUnits.DAY -> value * DAY
        }
        this.time = time
        return this
    }

    const val SECOND = 1000L
    const val MINUTE = 60 * SECOND
    const val HOUR = 60 * MINUTE
    const val DAY = 24 * HOUR

    enum class TimeUnits {
        SECOND, MINUTE, HOUR, DAY;

        fun plural(value: Int): String {
            val x = lastIndexOf(value.toLong())
            return when (this) {
                SECOND -> if (x < 2) "$value секунду" else if (x < 5) "$value секунды" else "$value секунд"
                MINUTE -> if (x < 2) "$value минуту" else if (x < 5) "$value минуты" else "$value минут"
                HOUR -> if (x < 2) "$value час" else if (x < 5) "$value часа" else "$value часов"
                DAY -> if (x < 2) "$value день" else if (x < 5) "$value дня" else "$value дней"
            }
        }
    }