package my.phatndt.xsmart.android.core.ui.widget.textfield

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import my.phatndt.xsmart.share.common.amount.AmountFormatter

class AmountVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text
        val formattedText = AmountFormatter.toDisplayAmount(rawText)
        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = AmountOffsetMapping(rawText, formattedText)
        )
    }

    private class AmountOffsetMapping(
        private val originalText: String,
        private val formattedText: String
    ) : OffsetMapping {

        private val groupingSeparator = AmountFormatter.decimalFormatSymbols.groupingSeparator

        override fun originalToTransformed(offset: Int): Int {
            // Handle edge cases
            if (offset <= 0) return 0
            if (offset >= originalText.length) return formattedText.length

            var transformedOffset = 0
            var originalCharsSeen = 0

            for (char in formattedText) {
                if (char != groupingSeparator) {
                    if (originalCharsSeen >= offset) {
                        break
                    }
                    originalCharsSeen++
                }
                transformedOffset++
            }

            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            // Handle edge cases
            if (offset <= 0) return 0
            if (offset >= formattedText.length) return originalText.length

            var originalOffset = 0

            for (i in 0 until offset) {
                if (formattedText[i] != groupingSeparator) {
                    originalOffset++
                }
            }

            return originalOffset.coerceAtMost(originalText.length)
        }
    }
}
