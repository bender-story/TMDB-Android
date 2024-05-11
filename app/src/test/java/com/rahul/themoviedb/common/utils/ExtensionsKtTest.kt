package com.rahul.themoviedb.common.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsKtTest{
    @Test
    fun `test zero minutes`() {
        assertEquals("0h 0m", 0.convertMinutesToHoursAndMinutes())
    }

    @Test
    fun `test less than one hour`() {
        assertEquals("0h 45m", 45.convertMinutesToHoursAndMinutes())
    }
    @Test
    fun `test hours and minutes`() {
        assertEquals("3h 15m", 195.convertMinutesToHoursAndMinutes())
    }

    @Test
    fun `test large number`() {
        assertEquals("24h 0m", 1440.convertMinutesToHoursAndMinutes())
    }

    @Test
    fun `test zero`() {
        assertEquals("0.0", 0.0.formatToOneDecimalPlaces())
    }

    @Test
    fun `test typical decimal`() {
        assertEquals("3.6", 3.56.formatToOneDecimalPlaces())
    }

    @Test
    fun `test exactly at half`() {
        assertEquals("2.5", 2.5.formatToOneDecimalPlaces())
    }

    @Test
    fun `test negative number`() {
        assertEquals("-1.3", (-1.25).formatToOneDecimalPlaces())
    }

    @Test
    fun `test very small decimal`() {
        assertEquals("0.1", 0.123456.formatToOneDecimalPlaces())
    }

    @Test
    fun `test no decimal`() {
        assertEquals("7.0", 7.0.formatToOneDecimalPlaces())
    }
}