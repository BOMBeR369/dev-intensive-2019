package ru.skillbranch.devintensive.utils

object Utils {
    val TransliterationMap: Map<String, String> = mapOf(
        "А" to "A", "а" to "a",
        "Б" to "B", "б" to "b",
        "В" to "V", "в" to "v",
        "Г" to "G", "г" to "g",
        "Д" to "D", "д" to "d",
        "Е" to "E", "е" to "e",
        "Ё" to "E", "ё" to "e",
        "Ж" to "Zh", "ж" to "zh",
        "З" to "Z", "з" to "z",
        "И" to "I", "и" to "i",
        "Й" to "I", "й" to "i",
        "К" to "K", "к" to "k",
        "Л" to "L", "л" to "l",
        "М" to "M", "м" to "m",
        "Н" to "N", "н" to "n",
        "О" to "O", "о" to "o",
        "П" to "P", "п" to "p",
        "Р" to "R", "р" to "r",
        "С" to "S", "с" to "s",
        "Т" to "T", "т" to "t",
        "У" to "U", "у" to "u",
        "Ф" to "F", "ф" to "f",
        "Х" to "H", "х" to "h",
        "Ц" to "C", "ц" to "c",
        "Ч" to "Ch", "ч" to "ch",
        "Ш" to "Sh", "ш" to "sh",
        "Щ" to "Sh'", "щ" to "sh'",
        "Ъ" to "", "ъ" to "",
        "Ы" to "I", "ы" to "i",
        "Ь" to "", "ь" to "",
        "Э" to "E", "э" to "e",
        "Ю" to "Yu", "ю" to "yu",
        "Я" to "Ya", "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return if (fullName.isNullOrBlank())
            null to null
        else {
            val list = fullName.split(" ")
            if (list.size > 1)
                list[0] to list[1]
            else
                list[0] to null
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        fun getInitial(expr: String) = expr[0].toUpperCase()
        return if (!firstName.isNullOrBlank() && !lastName.isNullOrBlank())
            "${getInitial(firstName)}${getInitial(lastName)}"
        else if (!firstName.isNullOrBlank())
            "${getInitial(firstName)}"
        else if (!lastName.isNullOrBlank())
            "${getInitial(lastName)}"
        else
            null
    }

    fun transliteration(payload: String, divider: String = " "): String {
        fun translitWord(word: String): String {
            val sb = StringBuilder()
            for (s in word) sb.append(TransliterationMap[s.toString()] ?: s)
            return sb.toString()
        }

        return payload.split(" ").map { translitWord(it) }.joinToString(separator = divider)
    }
}