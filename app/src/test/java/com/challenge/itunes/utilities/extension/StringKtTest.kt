package com.challenge.itunes.utilities.extension

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringKtTest {

    @Test
    fun `generate initials with empty string`() {
        val emptyString = ""
        val result = emptyString.getInitialLetters()
        assertThat(result).isEqualTo(emptyString)
    }

    @Test
    fun `generate initials with digits and string`() {
        val digitAndString = "1 up"
        val result = digitAndString.getInitialLetters()
        assertThat(result).isEqualTo("1u")
    }

    @Test
    fun `generate initials with digits only`() {
        val digitsOnly = "123 456 789"
        val result = digitsOnly.getInitialLetters()
        assertThat(result).isEqualTo("147")
    }

    @Test
    fun `generate initials with null variable`() {
        val nullString: String? = null
        val result = nullString?.getInitialLetters()
        assertThat(result).isEqualTo(null)
    }

}