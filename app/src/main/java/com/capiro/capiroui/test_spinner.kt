import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.capiroui.R
import com.capiro.composables.athomic_composables.SpinnerButtonCapiro
import com.capiro.composables.athomic_composables.SpinnerSimpleCapiro

@Preview(showBackground = true)
@Composable
fun TestSpinners() {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SpinnerButtonCapiro(
            labelIdResource = R.string.app_name,
            itemSelected = "item selected",
            onClicked = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
        )

        SpinnerButtonCapiro(
            labelIdResource = R.string.app_name,
            itemSelected = "item selected",
            onClicked = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
        )

        SpinnerButtonCapiro(
            labelIdResource = R.string.app_name,
            itemSelected = "item selected",
            onClicked = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
        )

        Row(modifier = Modifier.fillMaxWidth()) {

            Box(modifier = Modifier.weight(1f)) {
                SpinnerButtonCapiro(
                    labelIdResource = R.string.app_name,
                    itemSelected = "item selected",
                    onClicked = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
                )

            }

            Box(modifier = Modifier.weight(1f)) {
                SpinnerButtonCapiro(
                    labelIdResource = R.string.app_name,
                    itemSelected = "item selected",
                    onClicked = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
                )

            }


        }

    }
}