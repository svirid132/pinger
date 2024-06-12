import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowState
import java.net.InetAddress
import androidx.compose.material.icons.filled.Menu  // ok
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun App() {
    val viewModel = remember { IPv4Model() }
    val ipv4RawList = viewModel.state.collectAsState().value.okPingIPv4
    val ipv4List = ipv4RawList.map { it.toStr() }

    MaterialTheme {
        Box(modifier = Modifier.background(Color.Gray).padding(all = 12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                        .weight(3f)
                        .fillMaxHeight()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(modifier = Modifier.padding(start = 8.dp), text = "Successfully pinged addresses")
                        Spacer(modifier = Modifier.height(7.dp))
                        List(modifier = Modifier.weight(1f).fillMaxWidth(), ipv4List)
                    }
                } //leftBox
                RightBox(
                    modifier = Modifier.weight(2f).fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                        .padding(all = 12.dp),
                    viewModel = viewModel
                ) //RightBox
            }//Row
        }//Box
    }//MaterialTheme
}//app

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(width = 500.dp, height = 500.dp), title = "pinger") {
        App()
    }
}

