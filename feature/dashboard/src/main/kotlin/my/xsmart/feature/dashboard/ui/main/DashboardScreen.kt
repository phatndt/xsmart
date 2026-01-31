package my.xsmart.feature.dashboard.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.xsmart.feature.dashboard.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.xsmart.feature.dashboard.ui.main.state.DashboardUiEffect
import my.xsmart.feature.dashboard.ui.main.state.DashboardUiIntent
import my.xsmart.feature.dashboard.ui.main.state.DashboardUiState
import my.xsmart.share.ui.extension.paddingHorizontalLarge
import my.xsmart.share.ui.theme.AppTheme
import my.xsmart.share.ui.theme.spacing
import my.xsmart.share.ui.widget.XSmartButton
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel = koinViewModel(),
    onNavigationEvent: (DashboardNavigationEvent) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = UUID.randomUUID()) {
        viewModel.effect.collect { effect ->
            when (effect) {
                DashboardUiEffect.NavigateToSalaryCalculator -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToSalaryCalculator)
                }
                DashboardUiEffect.NavigateToTaxInfo -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToTaxInfo)
                }
                DashboardUiEffect.NavigateToOvertimeTracker -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToOvertimeTracker)
                }
                DashboardUiEffect.NavigateToInsurance -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToInsurance)
                }
                DashboardUiEffect.NavigateToHistory -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToHistory)
                }
                DashboardUiEffect.NavigateToHolidays -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToHolidays)
                }
                DashboardUiEffect.NavigateToNotifications -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToNotifications)
                }
                DashboardUiEffect.NavigateToEditTools -> {
                    onNavigationEvent(DashboardNavigationEvent.NavigateToEditTools)
                }
            }
        }
    }

    DashboardScreen(
        uiState = uiState.value,
        onAction = viewModel::setIntent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreen(
    uiState: DashboardUiState = DashboardUiState(),
    onAction: (DashboardUiIntent) -> Unit = {},
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                hasNotifications = uiState.hasNotifications,
                onNotificationClick = { onAction(DashboardUiIntent.OnNotificationClicked) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(bottom = MaterialTheme.spacing.bottomBarSpace),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xl),
        ) {
//            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
//
//            // Financial Summary Card
//            FinancialSummaryCard(
//                onStartCalculating = { onAction(DashboardUiIntent.OnStartCalculatingClicked) },
//            )

            // Quick Tools Section
            QuickToolsSection(
                onSalaryCalcClick = { onAction(DashboardUiIntent.OnSalaryCalcClicked) },
                onTaxClick = { onAction(DashboardUiIntent.OnTaxClicked) },
                onOvertimeClick = { onAction(DashboardUiIntent.OnOvertimeClicked) },
                onInsuranceClick = { onAction(DashboardUiIntent.OnInsuranceClicked) },
                onHistoryClick = { onAction(DashboardUiIntent.OnHistoryClicked) },
                onHolidaysClick = { onAction(DashboardUiIntent.OnHolidaysClicked) },
                onEditClick = { onAction(DashboardUiIntent.OnEditToolsClicked) },
            )

            // Daily Insight Section
//            DailyInsightCard()

            // Recent Updates Section
//            RecentUpdatesSection()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    hasNotifications: Boolean = true,
    onNotificationClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // User Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

                Column {
                    Text(
                        text = stringResource(R.string.dashboard_greeting_hello),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = stringResource(R.string.dashboard_greeting_have_nice_day),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        actions = {
//            BadgedBox(
//                    badge = {
//                        if (hasNotifications) {
//                            Badge(
//                                modifier = Modifier.size(8.dp),
//                                containerColor = MaterialTheme.colorScheme.error,
//                            )
//                        }
//                    },
//            ) {
//            IconButton(onClick = onNotificationClick) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Notifications",
//                )
//            }
//        }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
    )
}

@Composable
private fun FinancialSummaryCard(
    onStartCalculating: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF2563EB), // blue-600
                            Color(0xFF4F46E5), // indigo-600
                        ),
                    ),
                    shape = RoundedCornerShape(16.dp),
                ),
        ) {
            // Decorative blur circles
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .offset(x = (-16).dp, y = (-16).dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f))
                    .blur(12.dp),
            )
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .offset(x = (-16).dp, y = 16.dp)
                    .align(Alignment.BottomStart)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f))
                    .blur(12.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.xl),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg),
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxs),
                    ) {
                        Text(
                        text = stringResource(R.string.dashboard_financial_summary),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                    Text(
                        text = stringResource(R.string.dashboard_safe_mode_active),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f),
                    )
                    }

                    // Lock icon badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier.padding(MaterialTheme.spacing.xs),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Lock",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(6.dp)
                                .size(20.dp),
                        )
                    }
                }

                // Regulations info
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Verified,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp),
                        )
                        Text(
                            text = stringResource(R.string.dashboard_using_2024_regulations),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp),
                        )
                        Text(
                            text = stringResource(R.string.dashboard_region_i_hanoi_hcmc),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                    }
                }

                // Start Calculating Button
                XSmartButton(
                    onClick = onStartCalculating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.sm),
//                    containerColor = Color.White,
//                    contentColor = Color(0xFF0D7FF2),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Calculate,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                    Text(
                        text = stringResource(R.string.dashboard_start_calculating),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickToolsSection(
    onSalaryCalcClick: () -> Unit = {},
    onTaxClick: () -> Unit = {},
    onOvertimeClick: () -> Unit = {},
    onInsuranceClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onHolidaysClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.paddingHorizontalLarge(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg),
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.dashboard_quick_tools),
                style = MaterialTheme.typography.titleLarge,
            )
        }

        // Main Salary Calculator Card (Full Width)
        QuickToolCard(
            title = stringResource(R.string.dashboard_tool_salary_calc),
            subtitle = stringResource(R.string.dashboard_tool_salary_calc_subtitle),
            icon = Icons.Outlined.Money,
            iconBackgroundColor = Color(0xFF0D7FF2).copy(alpha = 0.1f),
            iconTint = Color(0xFF0D7FF2),
            onClick = onSalaryCalcClick,
            isLarge = true,
        )

        // Grid of smaller tools (2 columns)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        ) {
            QuickToolCard(
                title = stringResource(R.string.dashboard_tool_tax_info),
                subtitle = stringResource(R.string.dashboard_tool_tax_info_subtitle),
                icon = Icons.Outlined.MonetizationOn,
                iconBackgroundColor = Color(0xFFFFEDD5),
                iconTint = Color(0xFFF97316),
                onClick = onTaxClick,
                modifier = Modifier.weight(1f),
                enabled = false,
            )
            QuickToolCard(
                title = stringResource(R.string.dashboard_tool_overtime),
                subtitle = stringResource(R.string.dashboard_tool_overtime_subtitle),
                icon = Icons.Outlined.Schedule,
                iconBackgroundColor = Color(0xFFDCFCE7),
                iconTint = Color(0xFF16A34A),
                onClick = onOvertimeClick,
                modifier = Modifier.weight(1f),
                enabled = false,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        ) {
            QuickToolCard(
                title = stringResource(R.string.dashboard_tool_insurance),
                subtitle = stringResource(R.string.dashboard_tool_insurance_subtitle),
                icon = Icons.Outlined.HealthAndSafety,
                iconBackgroundColor = Color(0xFFDBEAFE),
                iconTint = Color(0xFF2563EB),
                onClick = onInsuranceClick,
                modifier = Modifier.weight(1f),
                enabled = false,
            )
            QuickToolCard(
                title = stringResource(R.string.dashboard_tool_history),
                subtitle = stringResource(R.string.dashboard_tool_history_subtitle),
                icon = Icons.Outlined.History,
                iconBackgroundColor = Color(0xFFF3E8FF),
                iconTint = Color(0xFF9333EA),
                onClick = onHistoryClick,
                modifier = Modifier.weight(1f),
                enabled = false,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        ) {
            QuickToolCard(
                title = stringResource(R.string.dashboard_tool_holidays),
                subtitle = stringResource(R.string.dashboard_tool_holidays_subtitle),
                icon = Icons.Outlined.CalendarMonth,
                iconBackgroundColor = Color(0xFFFCE7F3),
                iconTint = Color(0xFFDB2777),
                onClick = onHolidaysClick,
                modifier = Modifier.weight(1f),
                enabled = false,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuickToolCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconBackgroundColor: Color,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLarge: Boolean = false,
    enabled: Boolean = true,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF3F4F6)),
        enabled = enabled,
    ) {
        if (isLarge) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(28.dp),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(24.dp),
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(24.dp),
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun DailyInsightCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0E7FF)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFEEF2FF),
                            Color(0xFFEFF6FF),
                        ),
                    ),
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(17.dp),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg),
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0E7FF)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.TrendingUp,
                        contentDescription = null,
                        tint = Color(0xFF4F46E5),
                        modifier = Modifier.size(20.dp),
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxs),
                ) {
                    Text(
                        text = stringResource(R.string.dashboard_insight_overtime_holidays),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(R.string.dashboard_insight_overtime_description),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Row {
                        Text(
                            text = stringResource(R.string.dashboard_insight_overtime_rate),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4F46E5),
                        )
                        Text(
                            text = stringResource(R.string.dashboard_insight_overtime_suffix),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecentUpdatesSection() {
    Column(
        modifier = Modifier.paddingHorizontalLarge(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg),
    ) {
        Text(
            text = stringResource(R.string.dashboard_recent_updates),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF3F4F6)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF3F4F6)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Newspaper,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = stringResource(R.string.dashboard_update_social_insurance),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = stringResource(R.string.dashboard_update_effective_date),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Text(
                    text = stringResource(R.string.dashboard_update_time_ago),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DashboardScreenPreview() {
    AppTheme {
        DashboardScreen(
            uiState = DashboardUiState(),
        )
    }
}
