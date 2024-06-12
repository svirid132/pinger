import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun TextFieldWithError(
    value: String = "",
    onValueChanged: (String) -> Unit,
    errorText: String = "",
    isError: Boolean = false,
    trailingIcon: (@Composable () -> Unit)?,
    label: String = "",
    modifier: Modifier = Modifier
) {
    Column {
        TextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChanged,
            singleLine = true,
            isError = isError,
            trailingIcon = trailingIcon,
            label = { Text(label) }
        )
    }
    Text(
        fontSize = 10.sp,
        text = if (isError) errorText else "",
        modifier = Modifier.padding(start = 8.dp),
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption
    ) //Text ошибка }
}