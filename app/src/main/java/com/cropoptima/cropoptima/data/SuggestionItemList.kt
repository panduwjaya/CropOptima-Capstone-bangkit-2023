package com.cropoptima.cropoptima.data

object SuggestionItemList {
    val suggestionItemList = mutableListOf<SuggestionItem>()

    init {
        for (i in 1..22) {
            val suggestionItem = SuggestionItem(
                "item $i",
                "Suggestion item $i description",
                "https://picsum.photos/720?random=$i",
                "https://picsum.photos/720"
            )
            suggestionItemList.add(suggestionItem)
        }
    }
}
