import org.junit.jupiter.api.Assertions.*

class IPv4Test {

    @org.junit.jupiter.api.Test
    fun toStr() {
        val expected = "192.0.0.127"
        assertEquals(expected, IPv4(expected).toStr())
    }

    @org.junit.jupiter.api.Test
    fun inc() {
        val expected = "192.0.1.0"
        val value = "192.0.0.0"
        var ipv4 = IPv4(value)
        for (i in 0..255) {
            ++ipv4
        }
        assertEquals(expected, ipv4.toStr())
    }

    @org.junit.jupiter.api.Test
    fun minus() {
        val expected = 256
        assertEquals(expected, IPv4("192.0.1.0") - IPv4("192.0.0.0"))
    }
}