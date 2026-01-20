package my.xsmart.share.ui.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import my.xsmart.share.ui.theme.Spacing

@Composable
fun XSmartSpacerXLarge(modifier: Modifier = Modifier) = Spacer(modifier = modifier.height(Spacing.xLarge))

@Composable
fun XSmartSpacerLarge(modifier: Modifier = Modifier) = Spacer(modifier = modifier.height(Spacing.large))

@Composable
fun XSmartSpacerMedium(modifier: Modifier = Modifier) = Spacer(modifier = modifier.height(Spacing.medium))
