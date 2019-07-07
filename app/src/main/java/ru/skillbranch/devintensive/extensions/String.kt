package ru.skillbranch.devintensive.extensions

fun String.truncate(threshold: Int = 16): String {
    val filler = "..."
    var value = this.trimEnd()
    if (value.length > threshold)
        value = value.take(threshold).trimEnd() + filler
    return value
}