import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*

@Composable
fun RightBox(modifier: Modifier, viewModel: IPv4Model) {
    Box(
        modifier = modifier
    ) {
        var fromIPv4Str by remember { mutableStateOf("127.0.0.1") }
        var isFromIPv4Error by remember { mutableStateOf(false) }
        var toIPv4Str by remember { mutableStateOf("127.0.0.100") }
        var isToIPv4Error by remember { mutableStateOf(false) }
        var isErrorDialog by remember { mutableStateOf(false) }
        var job by remember { mutableStateOf<Job?>(null) }
        Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Text(text = "Range of IP addresses:")
            Spacer(modifier = Modifier.height(7.dp))
            TextFieldWithError(
                label = "From",
                value = fromIPv4Str,
                onValueChanged = {
                    fromIPv4Str = it
                    isFromIPv4Error = false
                    job?.cancel()
                    job = null
                }, errorText = "change ip",
                isError = isFromIPv4Error,
                trailingIcon = if (isFromIPv4Error) {
                    { ErrorIcon() }
                } else {
                    null
                },
                modifier = Modifier.fillMaxWidth()
            ) //TextFieldWithError
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldWithError(
                value = toIPv4Str,
                onValueChanged = {
                    toIPv4Str = it
                    isToIPv4Error = false
                    job?.cancel()
                    job = null
                },
                errorText = "change ip",
                isError = isToIPv4Error,
                trailingIcon = if (isToIPv4Error) {
                    { ErrorIcon() }
                }else {
                    null
                },
                label = "To",
                modifier = Modifier.fillMaxWidth()
            ) //TextFieldWithError
            Text(text = "Current ip:")
            Text(text = viewModel.currentIP)
            Spacer(modifier = Modifier.weight(1.0f))

            val coroutineScope = rememberCoroutineScope()

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                if (job != null) {
                    viewModel.stop = !viewModel.stop
                    return@Button
                }
                val fromCatch = "fromIPv4"
                val toCatch = "toIPv4"
                val rangeCatch = "range"
                try {
                    val fromIPv4 = try {
                        IPv4(fromIPv4Str)
                    } catch (e: Exception) {
                        throw Exception(fromCatch)
                    }
                    val toIPv4 = try {
                        IPv4(toIPv4Str)
                    } catch (e: Exception) {
                        throw Exception(toCatch)
                    }
                    if (fromIPv4 > toIPv4) {
                        throw Exception(rangeCatch)
                    }
                    job = coroutineScope.launch {
                        viewModel.stop = false
                        viewModel.pingIPv4s(fromIPv4, toIPv4)
                        job = null
                    }
                } catch (e: Exception) {
                    if (e.message == fromCatch) isFromIPv4Error = true
                    if (e.message == toCatch) isToIPv4Error = true
                    if (e.message == rangeCatch) isErrorDialog = true
                }
            }) {
                val equal1 = job?.isActive ?: false
                Text(if (equal1 && !viewModel.stop) "Stop" else "Start")
            }
            if (isErrorDialog) {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        Button(onClick = { isErrorDialog = false }) {
                            Text("OK")
                        }
                    },
                    title = { Text("Out of range") },
                    text = { Text("Range ${fromIPv4Str}..${toIPv4Str} is invalid. Change the IP so that the range becomes correct.") },
                )
            }
        }
    }
}