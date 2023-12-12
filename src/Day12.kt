import kotlin.math.max

fun main() {

  var dp = MutableList(110) { MutableList(110) { MutableList(40){-1L} } }

  fun dfs(str: String, nums: List<Int>, pos: Int, now: Int, j: Int): Long {

    if (dp[pos][now][j] != -1L) return dp[pos][now][j]


    if (pos == str.length) {
      var nnow = now
      var nj = j
      if (nnow > 0 && nj < nums.size && now == nums[nj]) {
        nj++
        nnow = 0
      }
      if (nj == nums.size && nnow == 0) {
        dp[pos][now][j] = 1
        return 1
      }
      else {
        dp[pos][now][j] = 0
        return 0
      }
    }

    if (j >= nums.size && now > 0) {
      dp[pos][now][j] = 0
      return 0
    }
    if (j < nums.size && now > nums[j]) {
      dp[pos][now][j] = 0
      return 0
    }

    if (str[pos] == '.') {
      if (now > 0) {
        if (j < nums.size && now == nums[j]) {
          return dfs(str, nums, pos+1, 0, j+1)
        } else {
          dp[pos][now][j] = 0
          return 0
        }
      }
      dp[pos][now][j] = dfs(str, nums, pos+1, now, j)
      return dp[pos][now][j]
    }
    if (str[pos] == '#') {
      dp[pos][now][j] = dfs(str, nums, pos+1, now+1, j)
      return dp[pos][now][j]
    }

    var tmp = dfs(str, nums, pos+1, now+1, j)
    if (now > 0) {
      if (j < nums.size && now == nums[j]) {
        tmp += dfs(str, nums, pos+1, 0, j+1)
      }
    } else {
      tmp += dfs(str, nums, pos+1, now, j)
    }
    dp[pos][now][j] = tmp
    return tmp
  }

  fun initializeDpArray() {
    for (i in 0 until dp.size) {
      for (j in 0 until dp[0].size) {
        for (k in 0 until dp[0][0].size) {
          dp[i][j][k] = -1
        }
      }
    }
  }

  fun part1(input: List<String>): Long {
    var ans = 0L
    for (line in input) {
      val str = line.substringBefore(" ")
      val nums = line.substringAfter(" ").split(',').map { it.toInt() }

      initializeDpArray()
      ans += dfs(str, nums, 0, 0, 0)
    }

    return ans
  }


  fun part2(input: List<String>): Long {
    var ans = 0L

    var maxLen = 0
    var maxSize = 0


    for (line in input) {
      val str = line.substringBefore(" ")
      val newStr = List(5) {str}.joinToString("?")
      val nums = line.substringAfter(" ").split(',').map { it.toInt() }
      var newNums = List(5) {nums}.flatten()


      initializeDpArray()
      ans += dfs(newStr, newNums, 0, 0, 0)
    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day12_test")
  check(part1(testInput) == 21L)
  part2(testInput).println()

  val input = readInput("Day12")
  part1(input).println()
  part2(input).println()
}
