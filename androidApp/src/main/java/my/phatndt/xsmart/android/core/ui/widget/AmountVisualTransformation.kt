package my.phatndt.xsmart.android.core.ui.widget

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import my.phatndt.xsmart.share.AmountFormatter

class AmountVisualTransformation(
    private val maxLength: Int,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = AmountFormatter.toDisplayAmount(text.text)
        val trimmedText = if (formattedText.length > maxLength) {
            AmountFormatter.toDisplayAmount(formattedText.take(maxLength))
        } else {
            formattedText
        }
        return TransformedText(
            text = AnnotatedString(trimmedText),
            offsetMapping = AmountOffsetMapping(trimmedText),
        )
    }

    inner class AmountOffsetMapping(private val formattedText: String) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var transformedOffset = 0
            var count = 0
            for (i in formattedText.indices) {
                if (formattedText[i] != AmountFormatter.decimalFormatSymbols.groupingSeparator) count++
                if (count > offset) break
                transformedOffset = i + 1
            }
            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var originalOffset = 0
            for (i in 0 until offset) {
                if (i >= formattedText.length) break
                if (formattedText[i] != AmountFormatter.decimalFormatSymbols.groupingSeparator) originalOffset++
            }
            return originalOffset
        }
    }
}
