import kotlin.math.max

fun main() {

  fun getNumbers(text: String): List<Pair<String, Int>> {
    return buildList {
      for (tmp in
      text.trim().split(",")
        .map { it.trim() }) {
        val num = tmp.split(' ').first().trim().toInt()
        val color = tmp.split(' ').last().trim()
        add(color to num)
      }
    }
  }

  fun isValid(colors: List<Pair<String, Int>>): Boolean {
    for (p in colors) {
      if (p.first == "red" && p.second > 12) {
        return false
      }
      if (p.first == "green" && p.second > 13) {
        return false
      }
      if (p.first == "blue" && p.second > 14) {
        return false
      }
    }
    return true
  }


  fun part1(input: List<String>): Int {
    var ans = 0
    for ((index, line) in input.withIndex()) {
      if (
        line.substringAfter(':').split(';')
          .none { !isValid(getNumbers(it)) }
      ) {
        ans += index+1
      }
    }

    return ans
  }

  fun part2(input: List<String>): Long {
    var ans = 0L

    for ((index, line) in input.withIndex()) {
      var blue = 0
      var green = 0
      var red = 0
      for (text in line.substringAfter(":").split(";")) {
        val numbers = getNumbers(text)
        for (p in numbers) {
          if (p.first == "red") {
            red = max(red, p.second)
          }
          if (p.first == "blue") {
            blue = max(blue, p.second)
          }
          if (p.first == "green") {
            green = max(green, p.second)
          }
        }
      }
      ans += red * blue * green
    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day02_test")
  check(part1(testInput) == 8)
  println(part2(testInput))

  val input = readInput("Day02")
  part1(input).println()
  part2(input).println()
}
