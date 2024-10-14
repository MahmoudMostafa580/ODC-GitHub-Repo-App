package com.mahmoud.githubrepos.presentation.screens.repoIssuesScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun IssueItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(modifier = modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_issues),
                contentDescription = null,
                modifier = modifier
                    .size(32.dp)
                    .clip(CircleShape),
            )
            Column(
                modifier = modifier.padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "Issue Title",
                        modifier = modifier.weight(1f),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = "Open", modifier = Modifier.padding(horizontal = 8.dp))
                }
                Text(text = "Issue label", modifier = modifier.padding(vertical = 4.dp))
                Row(
                    modifier = modifier.padding(vertical = 4.dp),
                ) {
                    Text(
                        text = "Created At:",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1)
                    Text(text = "2024-10-11,20:30 PM", modifier = modifier.padding(horizontal = 4.dp))
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    ODCGithubRepoAppTheme() {
        IssueItem()
    }
}