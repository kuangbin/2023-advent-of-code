fun main() {

  fun getHash(str: String): Int {
    var cur = 0
    for (cc in str) {
      cur = cur + (cc.code + 0)
      cur = (cur*17) % 256
    }
    return cur
  }

  fun part1(input: List<String>): Int {
    var ans = 0
    for (str in input[0].split(',')) {
      ans += getHash(str)
    }
    return ans
  }

  fun part2(input: List<String>): Int {

    val list = List(256) { mutableListOf<Pair<String, Int>>() }

    for (str in input[0].split(',')) {

      if (str.endsWith("-")) {
        val name = str.substringBefore("-")

        val box = getHash(name)

        val index = list[box].indexOfFirst { it.first == name }
        if (index != -1) {
          list[box].removeAt(index)
        }
      } else {

        val name = str.substringBefore("=")
        val value = str.substringAfter("=").toInt()

        val box = getHash(name)

        val index = list[box].indexOfFirst { it.first == name }
        if (index != -1) {
          list[box].set(index, name to value)
        } else {
          list[box].add(name to value)
        }
      }
    }

    println(list)

    var ans = 0
    for (i in 0 until 256) {
      for (j in 0 until list[i].size) {
        ans += (i+1) * (j+1) * list[i][j].second
      }
    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day15_test")
  check(part1(testInput) == 1320)

  part2(testInput).println()

  val input = readInput("Day15")
  part1(input).println()
  part2(input).println()

}
