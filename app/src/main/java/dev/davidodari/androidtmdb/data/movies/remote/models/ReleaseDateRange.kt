package dev.davidodari.androidtmdb.data.movies.remote.models

import java.util.Calendar


object ReleaseDateRangeFilterGenerator {

    fun generateReleaseDateRange(): ReleaseDateRange {
        val currentYear = getCurrentYear()
        val currentMonth = getCurrentMonth().padWithZero()
        val currentDay = getCurrentDay()
        return ReleaseDateRange(
            from = "$currentYear-01-01",
            to = "$currentYear-$currentMonth-$currentDay"
        )
    }

    private fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)

    private fun getCurrentMonth(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1

    private fun getCurrentDay(): Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

}

private fun Int.padWithZero(): Any {
    return if (this < 10) {
        "0$this"
    } else {
        this
    }
}

data class ReleaseDateRange(
    val from: String,
    val to: String
)
