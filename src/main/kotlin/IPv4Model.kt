import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import java.net.InetAddress

class IPv4Model {

    private val _state = MutableStateFlow(IPv4Data())
    val state = _state.asStateFlow()

    var stop by mutableStateOf(false)
    var currentIP by mutableStateOf("0.0.0.0")

    suspend fun pingIPv4s(from: IPv4, to: IPv4) {
        val count = to - from
        var currentIPv4 = from
        val mutList = mutableListOf<IPv4>()
        for (i in 0..count) {
            val str = currentIPv4.toStr()
            currentIP = str
            val inetAddress: InetAddress = withContext(Dispatchers.IO) {
                InetAddress.getByName(str)
            }
            while (stop) delay(100)
            println(str)
            if (withContext(Dispatchers.IO) {
                    inetAddress.isReachable(300)
                }) {
                mutList.add(currentIPv4)
            }
            if ((i % 10) == 0) {
                _state.update { currentState ->
                    currentState.copy(okPingIPv4 = mutList.toList())
                }
                delay(100)
            }
            ++currentIPv4
        }
        _state.update { currentState ->
            currentState.copy(okPingIPv4 = mutList.toList())
        }
    }
}