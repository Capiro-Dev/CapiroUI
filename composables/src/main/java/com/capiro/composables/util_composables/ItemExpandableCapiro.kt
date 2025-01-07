package com.capiro.composables.util_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.theme.GreenCapiro
import java.io.Serializable

@Composable
fun ItemExpandableCairo(
    itemNumber: String,
    customer: String,
    mainComposable: @Composable () -> Unit,
    listVarieties: List<VarietyReport>,
    color: Color,
    colorBackground: Color
) {
    // Estado para controlar si el ítem está expandido o no
    var isExpanded = remember { mutableStateOf(false) }

    Column {
        CardCapiro(
            backgroundColor = colorBackground,
            innerComposable = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ItemScannerHeader(itemNumber)

                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Top) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            ItemScannerLabel(color = color, label = customer)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .weight(1f)
                            ) {
                                mainComposable()
                            }
                            IconButton(
                                onClick = { isExpanded.value = !isExpanded.value },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = if (isExpanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                    contentDescription = if (isExpanded.value) "Collapse" else "Expand"
                                )
                            }
                        }
                    }
                }
            }
        )
        // Mostrar la lista solo si el estado de expansión es verdadero
        if (isExpanded.value) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(listVarieties.size, key = {
                    listVarieties[it].variety
                }
                ) { index ->
                    Column {
                        CardCapiro(
                            backgroundColor = colorBackground,
                            innerComposable = {
                                Row {
                                    ItemScannerHeader(itemNumber)
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 8.dp)
                                    ) {
                                        Text(
                                            text = listVarieties[index].variety,
                                            color = GreenCapiro,
                                            fontWeight = Bold,
                                            style = TypographyProvider.typography.bodyMedium,
                                        )
                                        Text("Tallos: ${listVarieties[index].stems}", color = GreenCapiro)
                                        Text("Ramos: ${listVarieties[index].bouquets}", color = GreenCapiro)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
}

data class VarietyReport(
    val variety: String,
    val stems: Int,
    val bouquets: Int
) : Serializable

@Preview
@Composable
fun TestItemExpandible() {

    val listVarieties = listOf(
        VarietyReport("holi", 1, 2),
        VarietyReport("variety2", 1, 2),
        VarietyReport("variety", 1, 22)
    )

    Column {
        ItemExpandableCairo(
            itemNumber = "c14",
            customer = "Dumment orange the netherlands BV",
            mainComposable = {
                Column {
                    Text("Tallos: 100")
                    Text("Ramos: 10")
                }
            },
            listVarieties = listVarieties,
            color = GreenCapiro,
            colorBackground = Color.White
        )

        ItemExpandableCairo(
            itemNumber = "c20",
            customer = "Dumment orange the netherlands BV",
            mainComposable = {
                Column {
                    Text("Tallos: 100")
                    Text("Ramos: 10")
                }
            },
            listVarieties = listVarieties,
            color = GreenCapiro,
            colorBackground = Color.White
        )

        ItemExpandableCairo(
            itemNumber = "c32",
            customer = "Dumment orange the netherlands BV",
            mainComposable = {
                Column {
                    Text("Tallos: 100")
                    Text("Ramos: 10")
                }
            },
            listVarieties = listVarieties,
            color = GreenCapiro,
            colorBackground = Color.White
        )

        ItemExpandableCairo(
            itemNumber = "c25",
            customer = "Dumment orange the netherlands BV",
            mainComposable = {
                Column {
                    Text("Tallos: 100")
                    Text("Ramos: 10")
                }
            },
            listVarieties = listVarieties,
            color = GreenCapiro,
            colorBackground = Color.White
        )
    }
}
