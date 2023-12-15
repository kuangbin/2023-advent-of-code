fun main() {

  fun checkHor(mp: List<String>, row: Int): Int {
    val n = mp.size
    val m = mp[0].length
    var ans = 0
    for (i in 0 until row) {
      val nn = row + (row-i-1)
      if (nn >= n) {
        continue
      }
      for (j in 0 until m) {
        if (mp[i][j] != mp[nn][j]) {
          ans++
        }
      }
    }
    return ans
  }

  fun checkVer(mp: List<String>, col: Int): Int {
    val n = mp.size
    val m = mp[0].length
    var ans = 0
    for (j in 0 until col) {
      val mm = col + (col-j-1)
      if (mm >= m) {
        continue
      }
      for (i in 0 until n) {
        if (mp[i][j] != mp[i][mm]) {
          ans++
        }
      }
    }
    return ans
  }

  fun getNumber(mp: List<String>): Int {
    val n = mp.size
    val m = mp[0].length
    var ans = 0
    for (i in 1 until n) {
      if (checkHor(mp, i) == 0) {
        ans += 100 * i
      }
    }
    for (j in 1 until m) {
      if (checkVer(mp, j) == 0) {
        ans += j
      }
    }
    return ans
  }


  fun part1(input: List<String>): Int {

    var ans = 0
    val mp = mutableListOf<String>()
    for (line in input) {
      if (line.isEmpty()) {
        ans += getNumber(mp)
        mp.clear()
      } else {
        mp.add(line)
      }
    }
    ans += getNumber(mp)

    return ans
  }

  fun getNumber2(mp: List<String>): Int {
    val n = mp.size
    val m = mp[0].length
    var ans = 0
    for (i in 1 until n) {
      if (checkHor(mp, i) == 1) {
        ans += 100 * i
      }
    }
    for (j in 1 until m) {
      if (checkVer(mp, j) == 1) {
        ans += j
      }
    }
    return ans
  }

  fun part2(input: List<String>): Int {
    var ans = 0
    val mp = mutableListOf<String>()
    for (line in input) {
      if (line.isEmpty()) {
        ans += getNumber2(mp)
        mp.clear()
      } else {
        mp.add(line)
      }
    }
    ans += getNumber2(mp)

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day13_test")
  check(part1(testInput) == 405)
  part2(testInput).println()

  val input = readInput("Day13")
  part1(input).println()
  part2(input).println()
}
