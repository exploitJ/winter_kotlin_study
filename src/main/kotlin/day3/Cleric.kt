package day3

import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Cleric(
    var name: String,
    val maxHp: Int = 50,
    var hp: Int = maxHp,
    val maxMp: Int = 10,
    var mp: Int = maxMp,
) {
    fun attack() {}

    fun selfAid() {
        if (mp - 5 < 0) {
            return
        }

        assert(hp > 0)
        hp = maxHp
        mp -= 5
    }

    fun pray(lengthInSeconds: Double): Int {
        if (lengthInSeconds == 0.0) {
            return 0
        } else if (lengthInSeconds < 0) {
            throw NegativeTimeException()
        }
        val compensation = Random.nextDouble(0.0, 2.0)
        val restoredMp = floor(lengthInSeconds + compensation).toInt()
        val overflow = max(0, restoredMp + mp - maxMp)

        mp = min(restoredMp + mp, maxMp)
        return restoredMp - overflow
    }

    class NegativeTimeException : Exception()
}