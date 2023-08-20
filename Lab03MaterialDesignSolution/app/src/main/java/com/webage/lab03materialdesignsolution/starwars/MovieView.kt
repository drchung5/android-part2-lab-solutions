package com.webage.lab03materialdesignsolution.starwars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieView(
    movieViewModel: MovieViewModel
) {

    val openDialog = remember { mutableStateOf(false ) }
    var dialogTitle = remember { mutableStateOf( "" ) }
    var dialogCrawl = remember { mutableStateOf("" ) }

    LaunchedEffect(Unit, block = {
        movieViewModel.getMovies()
    })

    val colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    )

    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        for ( movie in movieViewModel.movieWrapper.value.results!!) {

            val title = "Ep ${movie.episode_id}: ${movie.title}"

            ListItem(
                modifier = Modifier
                    .clickable(enabled = true) {
                        dialogTitle.value = title
                        dialogCrawl.value = movie.opening_crawl
                        openDialog.value = true
                    },
                colors = colors,
                headlineContent = {
                    Text("Ep ${movie.episode_id}: ${movie.title}")
                },
                supportingContent = { Text(trimCrawl(movie.opening_crawl, 70)) },
                trailingContent = { Text(movie.release_date.take(4)) },
                leadingContent = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Localized description",
                    )
                }
            )
            Divider(
                color = Color.White,
                modifier = Modifier.fillMaxWidth().height(5.dp)
            )
        }

    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            title = {
                Text(text = dialogTitle.value )
            },
            text = {
                Text(text = dialogCrawl.value )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

fun trimCrawl( crawl :String, chars :Int ) : String {
    return crawl.replace("\n","").take(chars)+"..."
}


