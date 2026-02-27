package ru.practicum.android.diploma.domain.converters

import ru.practicum.android.diploma.presentation.model.VacancyDescription

class DescriptionSplitter {
    fun splitText(text: String?): List<VacancyDescription>? {
        return text?.let {
            InternalSplitter(text).splitText()
        }
    }

    private class InternalSplitter(val text: String) {
        private val result = mutableListOf<VacancyDescription>()
        var currentItems = mutableListOf<String>()
        var currentHeader: String? = null

        fun splitText(): List<VacancyDescription> {
            val lines = lineToLines(text)
            for (line in lines) {
                if (isHeader(line)) {
                    if (currentItems.isNotEmpty()) {
                        currentItemToResult()
                        currentItems = mutableListOf()
                    }

                    currentHeader = line
                } else {
                    currentItems += pretifyItem(line)
                }
            }

            if (currentItems.isNotEmpty()) {
                currentItemToResult()
            }

            return result
        }

        private fun isHeader(text: String): Boolean = text.endsWith(":")

        private fun currentItemToResult() {
            result.add(
                VacancyDescription(
                    title = pretifyHeader(currentHeader),
                    items = currentItems
                )
            )
        }

        private fun pretifyHeader(text: String?): String? {
            return text?.dropLast(1)
        }

        private fun pretifyItem(text: String): MutableList<String> {
            // без заголовка идет обычный текст, который не нужно разбивать на элементы
            if (currentHeader == null) {
                return mutableListOf(text)
            }

            // с заголовком отдельные пункты, которые нужно изавить от лишних знаков пунктуации
            return mutableListOf(
                text
                    .trimStart { it == '-' || it.isWhitespace() }
                    .trimEnd { setOf(',', ';', '.').contains(it) || it.isWhitespace() }
            )
        }

        private fun lineToLines(text: String): List<String> {
            return text.split("\n").filter { it.isNotBlank() }
        }
    }
}
