fun main() {
  fun part1(input: List<String>): Long {
    var ans = 0L

    for (line in input) {

      val numbers = line.split(' ').map { it.toInt() }

      val list = mutableListOf(numbers)

      while (true) {
        if (list.last().all { it == 0 }) {
          break
        }
        val newNumbers = mutableListOf<Int>()
        for (i in 1 until list.last().size) {
          newNumbers.add(list.last()[i] - list.last()[i-1])
        }
        list.add(newNumbers)
      }
      var ret = 0
      for (i in list.size - 2 downTo 0) {
        ret += list[i].last()
      }
      ans += ret

    }

    return ans
  }

  fun part2(input: List<String>): Long {
    var ans = 0L

    for (line in input) {

      val numbers = line.split(' ').map { it.toInt() }

      val list = mutableListOf(numbers)

      while (true) {
        if (list.last().all { it == 0 }) {
          break
        }
        val newNumbers = mutableListOf<Int>()
        for (i in 1 until list.last().size) {
          newNumbers.add(list.last()[i] - list.last()[i-1])
        }
        list.add(newNumbers)
      }
      var ret = 0
      for (i in list.size - 2 downTo 0) {
        ret = list[i].first() - ret
      }
      ans += ret

    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day09_test")
  check(part1(testInput) == 114L)
  part2(testInput).println()

  val input = readInput("Day09")
  part1(input).println()
  part2(input).println()
}
