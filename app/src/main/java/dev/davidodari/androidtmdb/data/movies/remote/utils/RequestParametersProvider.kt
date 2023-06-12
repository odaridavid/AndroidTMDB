package dev.davidodari.androidtmdb.data.movies.remote.utils

import dev.davidodari.androidtmdb.data.movies.remote.models.ReleaseDateRange
import dev.davidodari.androidtmdb.data.movies.remote.models.SortOption
import java.util.Calendar
import javax.inject.Inject

class RequestParametersProvider @Inject constructor() {

    private var currentPage = 1

    fun getReleaseDateRange(): ReleaseDateRange {
        val currentYear = getCurrentYear()
        val currentMonth = getCurrentMonth().padWithZero()
        val currentDay = getCurrentDay()
        return ReleaseDateRange(
            from = "$currentYear-01-01",
            to = "$currentYear-$currentMonth-$currentDay"
        )
    }

    fun getSelectedSortOption(): SortOption = SortOption.POPULARITY

    fun getLanguage(): String = "en-US"

    fun getCurrentPage(): Int = currentPage

    fun incrementCurrentPage() {
        currentPage++
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
