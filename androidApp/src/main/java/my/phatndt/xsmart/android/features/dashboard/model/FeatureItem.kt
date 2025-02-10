package my.phatndt.xsmart.android.features.dashboard.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FeatureItem(
    val name: String,
    val icon: ImageVector,
    val type: FeatureType,
)
