fun main() {
  fun part1(input: List<String>): Int {
    return input.sumOf { str ->
      ((str.toCharArray().first { it in '0'..'9' } - '0') * 10 + (str.toCharArray()
        .last { it in '0'..'9' } - '0'))
    }
  }

  fun part2(input: List<String>): Int {
    val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var ans = 0
    for (str in input) {
      var first = -1
      var last = -1

      for ((index, value) in str.toCharArray().withIndex()) {
        if (value in '0' .. '9') {
          if (first == -1) first = value - '0'
          last = value - '0'
        } else {
          for ((num, numText) in numbers.withIndex()) {
            if (str.substring(index).startsWith(numText)) {
              if (first == -1) first = num + 1
              last = num + 1
            }
          }
        }
      }

      ans += first * 10 + last

    }
    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day01_test")
  check(part1(testInput) == 142)

  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
