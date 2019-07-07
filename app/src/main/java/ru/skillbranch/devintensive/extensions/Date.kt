package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        if (value < 0) throw IllegalStateException("Value must be 0 or higher")
        when (value) {
            1 -> {
                return when (this) {
                    SECOND -> "1 секунду"
                    MINUTE -> "1 минуту"
                    HOUR -> "1 час"
                    DAY -> "1 день"
                }
            }
            in 2..4 -> {
                return when (this) {
                    SECOND -> "$value секунды"
                    MINUTE -> "$value минуты"
                    HOUR -> "$value часа"
                    DAY -> "$value дня"
                }
            }
            else -> {
                return if (value > 20) {
                    "$value" + plural(value % 10).drop(1)
                } else {
                    when (this) {
                        SECOND -> "$value секунд"
                        MINUTE -> "$value минут"
                        HOUR -> "$value часов"
                        DAY -> "$value дней"
                    }
                }
            }
        }
    }
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, unit: TimeUnits): Date {
    this.time += when(unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

/*
0с - 1с "только что"

1с - 45с "несколько секунд назад"

45с - 75с "минуту назад"

75с - 45мин "N минут назад"

45мин - 75мин "час назад"

75мин 22ч "N часов назад"

22ч - 26ч "день назад"

26ч - 360д "N дней назад"

>360д "более года назад"
 */
fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = this.time - date.time
    val past = diff < 0
    val pattern = if (past) "%s назад" else "через %s"
    return abs(diff).let {
        when {
            it <= SECOND -> "только что"
            it <= 45 * SECOND -> pattern.format("несколько секунд")
            it <= 75 * SECOND -> pattern.format("минуту")
            it <= 45 * MINUTE -> pattern.format(TimeUnits.MINUTE.plural((it / MINUTE).toInt()))
            it <= 75 * MINUTE -> pattern.format("час")
            it <= 22 * HOUR -> pattern.format(TimeUnits.HOUR.plural((it / HOUR).toInt()))
            it <= 26 * HOUR -> pattern.format("день")
            it <= 360 * DAY -> pattern.format(TimeUnits.DAY.plural((it / DAY).toInt()))
            else -> if (past) "более года назад" else "более чем через год"
        }
    }
}

