package com.cropoptima.cropoptima.data

object SuggestionRecentlyList {
    val suggestionRecentlyList = mutableListOf<SuggestionRecently>()

    init {
        for (i in 1..5) {
            val suggestionItem = SuggestionRecently(
                "item $i",
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX$i",
                "https://picsum.photos/720?random=$i",
                "https://picsum.photos/720?random=$i",
            )
            suggestionRecentlyList.add(suggestionItem)
        }
    }
}
