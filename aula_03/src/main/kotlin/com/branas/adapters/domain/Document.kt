package com.branas.adapters.domain

private const val VALID_DOCUMENT_REGEX = "[^\\d ]"
private const val EMPTY = ""

data class Document(var value: String) {

    init {
        if (!isValidDocument()) throw Exception("Invalid document")
    }

    private fun isValidDocument(): Boolean {
        if (isBlank()) return false
        val doc = sanitizeDocument()
        if (isInvalidSize(doc)) return false
        if (isSameDocumentNumber(doc)) return false
        val dg1 = calculateDigit(doc, 10)
        val dg2 = calculateDigit(doc, 11)
        return extractCheckDigit(doc) == "${dg1}${dg2}"
    }

    private fun isBlank() = this.value.isBlank()
    private fun sanitizeDocument() = this.value.replace(VALID_DOCUMENT_REGEX, EMPTY)
    private fun isInvalidSize(doc: String) = doc.length != 11
    private fun isSameDocumentNumber(doc: String) =
        doc.toCharArray().all { it == doc[0] }

    private fun calculateDigit(doc: String, factor: Int): Int {
        var total = 0
        var fact = factor
        for (element in doc) {
            if (fact > 1) total += element.digitToInt() * fact--
        }
        val rest = total % 11
        return takeIf { rest < 2 }
            ?.let { 0 }
            ?: (11 - rest)
    }

    private fun extractCheckDigit(doc: String): String {
        return doc.substring(doc.length - 2, doc.length)
    }
}