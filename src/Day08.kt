import java.math.BigInteger

fun main() {
  fun part1(input: List<String>): Int {

    val path = input[0]

    val mp = mutableMapOf<String, Pair<String, String>>()
    for (i in 2 until input.size) {
      val tmp1 = input[i].substringBefore("=").trim()
      val tmp2 = input[i].substringAfter("(").substringBefore(",").trim()
      val tmp3 = input[i].substringAfter(",").substringBefore(")").trim()
      mp.put(tmp1, tmp2 to tmp3)
    }
    var now = "AAA"
    for (i in 0..10000000) {
      if (now == "ZZZ") {
        return i
      }
      val ch = path[i%(path.length)]
      if (ch == 'L') now = mp[now]!!.first
      else now = mp[now]!!.second
    }

    return -1
  }

  fun lcm(l: List<Int>): BigInteger {
    return l.map { BigInteger.valueOf(it.toLong()) }.reduce { acc, i ->  acc * i / acc.gcd(i)}
  }

  fun part2(input: List<String>): Int {
    val path = input[0]

    val list = mutableListOf<String>()

    val mp = mutableMapOf<String, Pair<String, String>>()
    for (i in 2 until input.size) {
      val tmp1 = input[i].substringBefore("=").trim()
      val tmp2 = input[i].substringAfter("(").substringBefore(",").trim()
      val tmp3 = input[i].substringAfter(",").substringBefore(")").trim()
      mp.put(tmp1, tmp2 to tmp3)

      if (tmp1.endsWith("A")) {
        list.add(tmp1)
      }
    }

    val stepsToFinal = list.map {
      var now = it
      var step = 0
      var i = 0
      while (true) {
        val ch = path[i]
        if (ch == 'L') now = mp[now]!!.first
        else now = mp[now]!!.second
        i = (i+1)%(path.length)
        step++
        if (now.endsWith("Z")) break
      }
      step
    }

    println(stepsToFinal)

    return -1
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day08_test")
  // check(part1(testInput) == 6)
  part2(testInput).println()

  val input = readInput("Day08")
  part1(input).println()
  part2(input).println()
}
