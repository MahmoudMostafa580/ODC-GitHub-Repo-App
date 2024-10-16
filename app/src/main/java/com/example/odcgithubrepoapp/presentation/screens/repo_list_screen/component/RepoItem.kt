package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoUiModelList
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun RepoItem(
    githubRepoUiModel: GithubReposUiModel,
    onRepoItemClicked: (String, String) -> Unit
) {
    val imageCrossFadeAnimationDuration = 1000
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onRepoItemClicked(githubRepoUiModel.ownerName, githubRepoUiModel.name)
            }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = githubRepoUiModel.avatarUrl).apply {
                        crossfade(imageCrossFadeAnimationDuration)
                        placeholder(R.drawable.ic_github_placeholser)
                        error(R.drawable.ic_github_placeholser)
                    }.build()
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .size(50.dp)
                .clip(CircleShape)
        )

        Column(
            Modifier.padding(12.dp)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = githubRepoUiModel.name,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = githubRepoUiModel.starsCount,
                    modifier = Modifier.padding(top = 4.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            Text(text = githubRepoUiModel.ownerName, color = MaterialTheme.colorScheme.onSurface)
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = githubRepoUiModel.description,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = if (isExpanded) 3 else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Show less" else "Show more",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        }

    }
}

@Preview
@Composable
private fun PreviewRepoItem() {
    ODCGithubRepoAppTheme {
        RepoItem(
            githubRepoUiModel = fakeRepoUiModelList.first(),
            onRepoItemClicked = { _, _ ->
            }
        )
    }
}