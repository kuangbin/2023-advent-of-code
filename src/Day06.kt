fun main() {

  fun part1(input: List<String>): Long {
    val times = input[0].substringAfter(":").trim().split(" ").filter { it.trim().isNotEmpty() }
      .map { it.trim().toInt() }
    val distances = input[1].substringAfter(":").trim().split(" ").filter { it.trim().isNotEmpty() }
      .map { it.trim().toInt() }
    var ans = 1L
    for (i in 0 until times.size) {
      val tt = times[i]
      val dd = distances[i]
      var tmp = 0
      for (j in 0..tt) {
        if (j * (tt - j) > dd)
          tmp++
      }
      ans *= tmp
    }
    return ans
  }

  fun part2(input: List<String>): Long {
    val time = input[0].substringAfter(":").trim().replace(" ", "").toLong()
    val distance = input[1].substringAfter(":").trim().replace(" ", "").toLong()
    var ans = 0L
    for (i in 0..time) {
      if ((1L * i * (time-i)) > distance) {
        ans++
      }
    }
    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day06_test")
  check(part1(testInput) == 288L)
  println(part2(testInput))

  val input = readInput("Day06")
  part1(input).println()
  part2(input).println()
}
