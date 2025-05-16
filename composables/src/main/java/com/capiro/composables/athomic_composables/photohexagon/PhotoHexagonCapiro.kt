package com.capiro.composables.athomic_composables.photohexagon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.photohexagon.PolygonCapiro
import com.capiro.composables.theme.GrayCapiro
import com.capiro.composables.theme.GreenCapiro

@Composable
fun PolygonCapiro(modifier: Modifier, imageContent: @Composable (modifier: Modifier) -> Unit = {}) {
    Box() {
        imageContent(modifier)
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.hexagono),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            )
    }

}

data class PestDetail(
    val name: String,
    val image: Int
)

@Preview
@Composable
fun previewPolygonCapiro() {

    val pestArray = arrayOf(
        PestDetail(name = "Acaro", image = R.drawable.acaro),
        PestDetail(name = "Botritis", image = R.drawable.botritis),
        PestDetail(name = "Mariquita", image = R.drawable.mariquita),
        PestDetail(name = "Mosca Blanca", image = R.drawable.mosca),
        PestDetail(name = "Nematodo", image = R.drawable.nematodo),
        PestDetail(name = "Hongos", image = R.drawable.hongos),
        PestDetail(name = "Mariposas", image = R.drawable.mariposa),
        PestDetail(name = "Mariquita", image = R.drawable.mariquita),
        PestDetail(name = "Minador", image = R.drawable.minador),
        PestDetail(name = "Roya Blanca", image = R.drawable.royablanca),
    )

    LazyVerticalGrid(
        modifier = Modifier
            .background(GrayCapiro)
            .padding(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pestArray.size) { item ->
            Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = pestArray[item].name,
                    color = GreenCapiro,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
                PolygonCapiro(
                    modifier = Modifier
                        .height(160.dp)
                        .width(160.dp)
                ) {
                    Image(
                        painter = painterResource(id = pestArray[item].image),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = it.padding(1.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }


}