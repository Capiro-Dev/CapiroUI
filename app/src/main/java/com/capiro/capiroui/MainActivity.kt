package com.capiro.capiroui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capiro.capiroui.ui.theme.CapiroUITheme
import com.capiro.composables.textfield.TextFieldAndTileRoundedBorderCapiro
import com.capiro.composables.textfield.TextFieldCapiro
import com.capiro.composables.textfield.TextFieldOutlinedCapiro
import com.capiro.composables.textfield.TextFieldRoundedBorderCapiro
import com.capiro.composables.util_composables.CurrentAccountMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapiroUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CapiroUITheme {
                        //TextFields()
                        Login()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFields() {
    //scrollableColumn
    val scrollState = rememberScrollState()
    val text = remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .verticalScroll(scrollState)
    ) {
        TextFieldCapiro(textInput = text.value,
            label = "TextFieldCapiro",
            onTextChangeEvent = { text.value = it })
        Spacer(modifier = Modifier.size(16.dp))
        TextFieldOutlinedCapiro(textInput = text.value,
            label = "TextFieldOutlinedCapiro",
            onTextChangeEvent = { text.value = it })
        Spacer(modifier = Modifier.size(16.dp))
        TextFieldRoundedBorderCapiro(
            textInput = text.value,
            onTextChangeEvent = { text.value = it },
            label = "TextFieldRoundedBorderCapiro"
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextFieldAndTileRoundedBorderCapiro(
            textInput = text.value,
            onTextChangeEvent = { text.value = it },
            label = "TextFieldAndTileRoundedBorderCapiro",
            title = "title",
        )
    }
}


@Composable
fun UserProfileCard(
    companyName: String,
    userName: String,
    initials: String,
    onLogoutClick: () -> Unit,
    onPrintersClick: () -> Unit,
    onBackupClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = companyName, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }
        Text(text = "Cerrar Sesión",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onLogoutClick() })
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = userName, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Impresoras",
                color = Color(0xFF00BFA5),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onPrintersClick() })

            Text(text = "Backup",
                color = Color(0xFF00BFA5),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onBackupClick() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileCardPreview() {
    CurrentAccountMenu(
        user = "User",
        onBackUpClick = { },
        onLogoutClick = { },
        onPrintersClick = {}
    )
}


