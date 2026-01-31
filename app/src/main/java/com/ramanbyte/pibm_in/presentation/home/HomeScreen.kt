package com.ramanbyte.pibm_in.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ramanbyte.pibm_in.data.model.BannerItem
import com.ramanbyte.pibm_in.data.model.NavigationItem
import com.ramanbyte.pibm_in.data.model.PibmInfo
import com.ramanbyte.pibm_in.ui.theme.PIBMTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Removed Scaffold and TopAppBar - Direct content
    when (val state = uiState) {
        is HomeUiState.Loading -> {
            LoadingScreen()
        }

        is HomeUiState.Success -> {
            HomeContent(
                banners = state.banners,
                pibmInfo = state.pibmInfo,
                navigationItems = state.navigationItems
            )
        }

        is HomeUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.retry() }
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Error",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    banners: List<BannerItem>,
    pibmInfo: PibmInfo,
    navigationItems: List<NavigationItem>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp) // Only bottom padding
    ) {
        // Banners Section - No top padding for full width effect
        if (banners.isNotEmpty()) {
            item {
                BannerSection(banners = banners)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // PIBM Info Section
        item {
            PibmInfoSection(pibmInfo = pibmInfo)
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Navigation Items Section
        item {
            Text(
                text = "Quick Links",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(navigationItems.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    NavigationCard(
                        item = item,
                        modifier = Modifier.weight(1f)
                    )
                }
                // Add spacer if odd number of items
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun BannerSection(banners: List<BannerItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(banners) { banner ->
            BannerCard(banner = banner)
        }
    }
}

@Composable
fun BannerCard(banner: BannerItem) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(banner.imageUrl),
                contentDescription = banner.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = banner.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                if (banner.subtitle.isNotEmpty()) {
                    Text(
                        text = banner.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
fun PibmInfoSection(pibmInfo: PibmInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = pibmInfo.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pibmInfo.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            if (pibmInfo.highlights.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                pibmInfo.highlights.forEach { highlight ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = highlight,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationCard(
    item: NavigationItem,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .height(120.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = getIconForName(item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Composable
fun getIconForName(iconName: String) = when (iconName.lowercase()) {
    "school" -> Icons.Default.School
    "book" -> Icons.Default.Book
    "work" -> Icons.Default.Work
    "people" -> Icons.Default.People
    "location_city" -> Icons.Default.LocationCity
    "contact_mail" -> Icons.Default.ContactMail
    "home" -> Icons.Default.Home
    "info" -> Icons.Default.Info
    "phone" -> Icons.Default.Phone
    else -> Icons.Default.ArrowForward
}

// ============================================
// PREVIEW FUNCTIONS
// ============================================

/**
 * Preview data provider for HomeContent
 */
class HomeContentPreviewParameterProvider : PreviewParameterProvider<HomeContentPreviewData> {
    override val values = sequenceOf(
        HomeContentPreviewData(
            banners = getSampleBanners(),
            pibmInfo = getSamplePibmInfo(),
            navigationItems = getSampleNavigationItems()
        )
    )
}

data class HomeContentPreviewData(
    val banners: List<BannerItem>,
    val pibmInfo: PibmInfo,
    val navigationItems: List<NavigationItem>
)

// Sample data for previews
fun getSampleBanners() = listOf(
    BannerItem(
        _id = "1",
        _imageUrl = "https://example.com/banner_1.png",
        _title = "Welcome to Ramanbyte",
        _subtitle = "Explore jobs, internships & opportunities"
    ),
    BannerItem(
        _id = "2",
        _imageUrl = "https://example.com/banner_2.png",
        _title = "Apply Faster",
        _subtitle = "Track applications in real time"
    ),
    BannerItem(
        _id = "3",
        _imageUrl = "https://example.com/banner_3.png",
        _title = "Get Notified",
        _subtitle = "Never miss an interview call"
    )
)

fun getSamplePibmInfo() = PibmInfo(
    _title = "Pune Institute of Business Management",
    _description = "PIBM is one of India's premier business schools, offering world-class management education.",
    _highlights = listOf(
        "AICTE Approved",
        "Industry-Integrated Curriculum",
        "100% Placement Assistance",
        "State-of-the-art Infrastructure"
    )
)


fun getSampleNavigationItems() = listOf(
    NavigationItem(1, "Admissions", "school", "https://pibm.in/admissions", 1),
    NavigationItem(2, "Courses", "book", "https://pibm.in/courses", 2),
    NavigationItem(3, "Placements", "work", "https://pibm.in/placements", 3),
    NavigationItem(4, "Faculty", "people", "https://pibm.in/faculty", 4),
    NavigationItem(5, "Campus", "location_city", "https://pibm.in/campus", 5),
    NavigationItem(6, "Contact Us", "contact_mail", "https://pibm.in/contact", 6)
)


// Preview for Home Content (Success State)
@Preview(name = "Home Content - Light", showBackground = true)
@Composable
fun PreviewHomeContent() {
    PIBMTheme {
        HomeContent(
            banners = getSampleBanners(),
            pibmInfo = getSamplePibmInfo(),
            navigationItems = getSampleNavigationItems()
        )
    }
}

@Preview(
    name = "Home Content - Dark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewHomeContentDark() {
    PIBMTheme {
        HomeContent(
            banners = getSampleBanners(),
            pibmInfo = getSamplePibmInfo(),
            navigationItems = getSampleNavigationItems()
        )
    }
}

// Preview for Loading State
@Preview(name = "Loading State", showBackground = true)
@Composable
fun PreviewLoadingScreen() {
    PIBMTheme {
        LoadingScreen()
    }
}

// Preview for Error State
@Preview(name = "Error State", showBackground = true)
@Composable
fun PreviewErrorScreen() {
    PIBMTheme {
        ErrorScreen(
            message = "Failed to load data. Please check your internet connection.",
            onRetry = {}
        )
    }
}

// Preview for Individual Components
@Preview(name = "Banner Card", showBackground = true)
@Composable
fun PreviewBannerCard() {
    PIBMTheme {
        BannerCard(
            banner = BannerItem(
                _id = "1",
                _imageUrl = "https://example.com/banner_1.png",
                _title = "Welcome to Ramanbyte",
                _subtitle = "Explore jobs, internships & opportunities"
            )
        )
    }
}

@Preview(name = "PIBM Info Section", showBackground = true)
@Composable
fun PreviewPibmInfoSection() {
    PIBMTheme {
        PibmInfoSection(pibmInfo = getSamplePibmInfo())
    }
}

@Preview(name = "Navigation Card", showBackground = true)
@Composable
fun PreviewNavigationCard() {
    PIBMTheme {
        NavigationCard(
            item = NavigationItem(1, "Admissions", "school", "https://pibm.in/admissions", 1),
            modifier = Modifier.width(150.dp)
        )
    }
}

@Preview(name = "Navigation Cards Row", showBackground = true)
@Composable
fun PreviewNavigationCardsRow() {
    PIBMTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NavigationCard(
                item = NavigationItem(1, "Admissions", "school", "https://pibm.in/admissions", 1),
                modifier = Modifier.weight(1f)
            )
            NavigationCard(
                item = NavigationItem(2, "Courses", "book", "https://pibm.in/courses", 2),
                modifier = Modifier.weight(1f)
            )
        }
    }
}