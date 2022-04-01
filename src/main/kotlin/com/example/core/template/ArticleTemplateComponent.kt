package com.example.core.template

fun ArticleScaffold.rating(score: Double?, scoredBy: Int? = null) {
    val actualScore = score?.toString() ?: "--.--"
    text("★ ")
    text(actualScore)
    if (scoredBy != null) {
        text(" ")
        text("($scoredBy)")
    }
}

fun ratingString(score: Double?, scoredBy: Int? = null): String {
    return ArticleScaffold().apply { rating(score, scoredBy) }.buildString()
}

fun ArticleScaffold.listItem(text: String?) {
    if (!text.isNullOrEmpty()) {
        line {
            text("• ")
            text(text)
        }
    }
}

fun ArticleScaffold.horizontalList(delimiter: String, vararg items: String?) {
    val nonNullItems = items.filterNotNull()
    if (nonNullItems.isNotEmpty()) {
        var bufDelimiter = ""
        nonNullItems.forEach { item ->
            text(bufDelimiter)
            text(item)
            bufDelimiter = delimiter
        }
    }
}