import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable

@Composable
fun ErrorIcon() {
    Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colors.error)
}