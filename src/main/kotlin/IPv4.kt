import kotlin.math.pow

class IPv4(strIPv4: String) {
    private var ipArr = strIPv4.split(".").map { str -> str.toUByte() }.toMutableList()
    init {
        if (ipArr.size != 4) {
            throw Exception("адрес не является IPv4")
        }
    }
    fun toStr(): String {
        return ipArr.joinToString (separator = ".")
    }
    operator fun inc(): IPv4 {
        var plusOne = true
        val newIpArr = ipArr.toMutableList()
        for (i in newIpArr.size-1 downTo 0) {
            if (plusOne) {
                newIpArr[i] = (newIpArr[i] + 1u).toUByte()
                plusOne = (newIpArr[i].toInt() == 0)
            } else {
                plusOne = false
            }
        }
        val newIpStr = newIpArr.joinToString (separator = ".")
        return IPv4( newIpStr )
    }
    operator fun minus(b: IPv4): Int {
        var res: Int = 0
        val lastInd = ipArr.size - 1
        for(i in 0 .. lastInd) {
            val multi = if (i == lastInd) 1 else 256.0.pow((lastInd - i).toDouble())
            res += (ipArr[i].toInt() - b.ipArr[i].toInt()) * multi.toInt()
        }
        return res
    }
    operator fun compareTo(b: IPv4): Int {
        return this - b
    }
}