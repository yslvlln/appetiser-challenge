package com.challenge.itunes.utilities.extension

fun String.getInitialLetters(): String {
    var initialsString = ""
    val originalString = this.split(" ")
    if (originalString.size < 0) {
        return this
    }
    for (word in originalString) {
        val initial = word.take(1)
        initialsString += initial
    }
    return initialsString
}