fun main() {
  fun <T> log(t: T) {
    // println(t)
  }

  fun getWinningNumbers(input: String): List<Int> {
    return input.substringAfter(':').substringBefore('|').trim()
      .split(' ')
      .map { it.trim() }
      .filter { it.isNotEmpty() }
      .map { it.toInt() }
  }

  fun getNumbersYouHave(input: String): List<Int> {
    log(input.substringAfter('|'))
    return input.substringAfter('|').trim().split(' ')
      .map { it.trim() }
      .filter { it.isNotEmpty() }
      .map { it.toInt() }
  }

  fun getMatchingNumber(winningNumbers: Set<Int>, numbers: List<Int>): Int {
    return numbers.count { winningNumbers.contains(it) }
  }

  fun part1(input: List<String>): Long {
    var ans = 0L
    for (inputStr in input) {
      val winningNumbers = getWinningNumbers(inputStr).toSet()
      val numbers = getNumbersYouHave(inputStr)
      val machingNumber = getMatchingNumber(winningNumbers, numbers)
      val tmp = if (machingNumber == 0) 0 else (1L shl (machingNumber - 1))
      log(tmp)
      ans += tmp
    }
    return ans
  }

  fun part2(input: List<String>): Long {
    val n = input.size
    val sum = Array<Int>(n) {0}
    sum[0] = 1
    var ans = 0L
    for ((index, inputStr) in input.withIndex()) {
      if (index > 0) {
        sum[index] += sum[index-1]
      }

      ans += sum[index]

      val winningNumbers = getWinningNumbers(inputStr).toSet()
      val numbers = getNumbersYouHave(inputStr)
      val machingNumber = getMatchingNumber(winningNumbers, numbers)

      if (machingNumber > 0) {
        if (index + 1 < n) {
          sum[index + 1] += sum[index]
        }
        if (index + 1 + machingNumber < n) {
          sum[index + 1 + machingNumber]  -= sum[index]
        }
      }
    }
    return ans
  }


  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day04_test")
  check(part1(testInput) == 13L)
  part2(testInput).println()

  val input = readInput("Day04")
  part1(input).println()
  part2(input).println()
}
