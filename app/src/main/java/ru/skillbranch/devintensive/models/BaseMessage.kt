package ru.skillbranch.devintensive.models

import java.lang.IllegalStateException
import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    protected fun isIncomingStr() = if (isIncoming) "получил" else "отправил"

    companion object AbstractFactory {
        var lastId = -1
        fun makeMessage(from: User, chat: Chat, date: Date, type: String, payload: Any, isIncoming: Boolean = false): BaseMessage {
            ++lastId
            return when (type) {
                "text" -> TextMessage("${lastId}", from, chat, isIncoming, date, payload as String)
                "image" -> ImageMessage("${lastId}", from, chat, isIncoming, date, payload as String)
                else -> throw IllegalStateException("Unknown message type")
            }
        }
    }
}