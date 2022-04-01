package com.example.core.template

fun article(messageBuilder: ArticleScaffold.() -> Unit): String {
    return ArticleScaffold().apply(messageBuilder).buildString()
}

class ArticleScaffold {

    val stringBuilder = StringBuilder()

    fun buildString() = stringBuilder.toString()
}

fun ArticleScaffold.paragraph(paragraphBuilder: ArticleScaffold.() -> Unit) {
    this.paragraphBuilder()
    stringBuilder.appendLine()
}

fun ArticleScaffold.text(text: String) {
    stringBuilder.append(text)
}

fun ArticleScaffold.bold(boldBuilder: ArticleScaffold.() -> Unit) {
    stringBuilder.append("<b>")
    this.boldBuilder()
    stringBuilder.append("</b>")
}

fun ArticleScaffold.line(lineBuilder: ArticleScaffold.() -> Unit) {
    this.lineBuilder()
    stringBuilder.append("\n")
}

fun ArticleScaffold.link(url: String, text: String? = null) {
    if (text == null) {
        stringBuilder.append(url)
    } else {
        stringBuilder.append("<a href=\"")
        stringBuilder.append(url)
        stringBuilder.append("\">")
        stringBuilder.append(text)
        stringBuilder.append("</a>")
    }
}