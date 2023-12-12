import kotlin.math.abs

fun main() {
  fun part1(input: List<String>): Long {
    val n = input.size
    val m = input[0].length
    val list = buildList {
      for (i in 0 until n) {
        for (j in 0 until m) {
          if (input[i][j] == '#') {
            add(i to j)
          }
        }
      }
    }

    val rows = List(n) {it + 1}.filter { i ->
      list.none { it.first == i }
    }
    val cols = List(m) {it+1}.filter { i ->
      list.none {it.second == i}
    }

    var ans = 0L
    for (p1 in list) {
      for (p2 in list) {
        ans += abs(p1.first - p2.first)
        ans += abs(p1.second - p2.second)

        ans += rows.count { it in p1.first..p2.first || it in p2.first..p1.first }
        ans += cols.count { it in p1.second..p2.second || it in p2.second..p1.second }
      }
    }


    return ans / 2

  }

  fun part2(input: List<String>): Long {
    val n = input.size
    val m = input[0].length
    val list = buildList {
      for (i in 0 until n) {
        for (j in 0 until m) {
          if (input[i][j] == '#') {
            add(i to j)
          }
        }
      }
    }

    val rows = List(n) {it + 1}.filter { i ->
      list.none { it.first == i }
    }
    val cols = List(m) {it+1}.filter { i ->
      list.none {it.second == i}
    }

    var ans = 0L
    for (p1 in list) {
      for (p2 in list) {
        ans += abs(p1.first - p2.first)
        ans += abs(p1.second - p2.second)

        ans += (1000000L-1) * rows.count { it in p1.first..p2.first || it in p2.first..p1.first }
        ans += (1000000L-1) * cols.count { it in p1.second..p2.second || it in p2.second..p1.second }
      }
    }


    return ans / 2

  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day11_test")
  check(part1(testInput) == 374L)
  part2(testInput).println()

  val input = readInput("Day11")
  part1(input).println()
  part2(input).println()
}
