package com.cropoptima.cropoptima.data

object SuggestionPlantList {
    val suggestionItemList = mutableListOf<SuggestionPlant>(
        SuggestionPlant(
            "rice",
            "Suggestion item description",
            "https://picsum.photos/720?random=1",
            "https://picsum.photos/720"
        ),
        SuggestionPlant(
            "maize",
            "Suggestion item description",
            "https://picsum.photos/720?random=1",
            "https://picsum.photos/720"
        ),
    )

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

    //        "rice",
    //        "maize",
    //        "chickpea",
    //        "kidneybeans",
    //        "pigeonpeas",
    //        "mothbeans",
    //        "mungbean",
    //        "blackgram",
    //        "lentil",
    //        "pomegranate",
    //        "banana",
    //        "mango",
    //        "grapes",
    //        "watermelon",
    //        "muskmelon",
    //        "apple",
    //        "orange",
    //        "papaya",
    //        "coconut",
    //        "cotton",
    //        "jute",
    //        "coffee",
}
