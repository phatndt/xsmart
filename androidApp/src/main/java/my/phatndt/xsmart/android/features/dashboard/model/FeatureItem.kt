package my.phatndt.xsmart.android.features.dashboard.model

import androidx.compose.ui.graphics.vector.ImageVector
import my.phatndt.xsmart.android.core.utils.DeferredText

data class FeatureItem(
    val name: DeferredText,
    val icon: ImageVector,
    val type: FeatureType,
)
