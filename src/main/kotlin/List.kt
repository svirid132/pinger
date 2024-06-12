import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun List(modifier: Modifier = Modifier, texts: List<String>) {
    val stateVertical = rememberScrollState(0)
//    val texts = listOf("123", "321", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555", "555")
//    val texts = listOf("123")
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .verticalScroll(stateVertical)
                .background(color = Color.White)
                .padding(all = 12.dp)
        ) {
            Column {
                texts.forEach { text ->
                    Text(text = text)
                }
            }
        } //Box
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(stateVertical)
        )
    } //Box(Box, VerticalScrollbar)
}