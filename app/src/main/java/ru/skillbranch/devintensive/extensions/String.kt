package ru.skillbranch.devintensive.extensions

fun String.truncate(threshold: Int = 16): String {
    val filler = "..."
    var value = this.trimEnd()
    if (value.length > threshold)
        value = value.take(threshold).trimEnd() + filler
    return value
}

fun String.stripHtml():String {
    val spaces = " {2,}".toRegex()
    val tag = "<.+?> *".toRegex()
    val escape = "&\\w+?; *".toRegex()
    val symbols = "[&'\"<>] *".toRegex()
    return this.replace(tag, "").replace(escape,"").replace(symbols, "").replace(spaces," ")
}