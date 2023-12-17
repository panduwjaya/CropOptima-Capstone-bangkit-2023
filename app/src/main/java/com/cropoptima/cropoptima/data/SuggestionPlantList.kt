package com.cropoptima.cropoptima.data

object SuggestionPlantList {
    val suggestionItemList = mutableListOf<SuggestionPlant>()

    init {
        for (i in 1..22) {
            val suggestionItem = SuggestionPlant(
                "item $i",
                "Suggestion item $i description",
                "https://picsum.photos/720?random=$i",
                "https://picsum.photos/720"
            )
            suggestionItemList.add(suggestionItem)
        }
    }
}
