import java.util.LinkedList
import java.util.Queue
import kotlin.math.max
import kotlin.math.min

fun main() {
  fun getShortestPath(input: List<String>, sx: Int, sy: Int): List<List<Int>> {
    val n = input.size
    val m = input[0].length
    val res = MutableList(n) { MutableList(m) { -1 } }

    res[sx][sy] = 1
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(sx to sy)
    while (queue.isNotEmpty()) {
      val now = queue.remove()
      val x = now.first
      val y = now.second
      // Top
      if (x > 0 && input[x][y] in listOf('|', 'L', 'J')
        && input[x - 1][y] in listOf('|', '7', 'F') && res[x-1][y] == -1
      ) {
        res[x-1][y] = res[x][y] + 1
        queue.add(x-1 to y)
      }

      // Right
      if (y+1 < m && input[x][y] in listOf('-', 'L', 'F')
        && input[x][y+1] in listOf('-', 'J', '7') && res[x][y+1] == -1
      ) {
        res[x][y+1] = res[x][y] + 1
        queue.add(x to y+1)
      }

      // Down
      if (x+1 < n && input[x][y] in listOf('|', 'F', '7')
        && input[x + 1][y] in listOf('|', 'L', 'J') && res[x+1][y] == -1
      ) {
        res[x+1][y] = res[x][y] + 1
        queue.add(x+1 to y)
      }

      // Left
      if (y > 0 && input[x][y] in listOf('-', 'J', '7')
        && input[x][y-1] in listOf('-', 'L', 'F') && res[x][y-1] == -1
      ) {
        res[x][y-1] = res[x][y] + 1
        queue.add(x to y-1)
      }
    }

    println(res)

    return res
  }


  fun part1(input: List<String>): Int {
    val n = input.size
    val m = input[0].length
    var sx = 0
    var sy = 0
    for (i in 0 until n)
      for (j in 0 until m) {
        if (input[i][j] == 'S') {
          sx = i
          sy = j
          break
        }
      }
    val res = mutableListOf<List<List<Int>>>()

    if (sx > 0 && input[sx-1][sy] in listOf('|', '7', 'F')) {
      res.add(getShortestPath(input, sx-1, sy))
    }
    if (sy > 0 && input[sx][sy-1] in listOf('-', 'L', 'F')) {
      res.add(getShortestPath(input, sx, sy-1))
    }
    if (sx+1 < n && input[sx+1][sy] in listOf('|', 'L', 'J')) {
      res.add(getShortestPath(input, sx+1, sy))
    }
    if (sy+1 < m && input[sx][sy+1] in listOf('-', '7', 'J')) {
      res.add(getShortestPath(input, sx, sy+1))
    }

    var ans = 0
    for (i in 0 until n)
      for (j in 0 until m) {
        if (res[0][i][j] != -1 && res[1][i][j] != -1) {
          ans = max(ans, min(res[0][i][j], res[1][i][j]))
        }
      }

    return ans

  }

  fun part2(input: List<String>): Int {
    val n = input.size
    val m = input[0].length
    var sx = 0
    var sy = 0
    for (i in 0 until n)
      for (j in 0 until m) {
        if (input[i][j] == 'S') {
          sx = i
          sy = j
          break
        }
      }
    val res = mutableListOf<List<List<Int>>>()

    if (sx > 0 && input[sx-1][sy] in listOf('|', '7', 'F')) {
      res.add(getShortestPath(input, sx-1, sy))
    }
    if (sy > 0 && input[sx][sy-1] in listOf('-', 'L', 'F')) {
      res.add(getShortestPath(input, sx, sy-1))
    }
    if (sx+1 < n && input[sx+1][sy] in listOf('|', 'L', 'J')) {
      res.add(getShortestPath(input, sx+1, sy))
    }
    if (sy+1 < m && input[sx][sy+1] in listOf('-', '7', 'J')) {
      res.add(getShortestPath(input, sx, sy+1))
    }

    var ans = 0
    for (i in 0 until n)
      for (j in 0 until m) {
        if (res[0][i][j] != -1 && res[1][i][j] != -1) {
          ans = max(ans, min(res[0][i][j], res[1][i][j]))
        }
      }

    val num = MutableList(n) {MutableList(m) {0} }
    for (i in 0 until n) {
      for ( j in 0 until m) {
        num[i][j] = 0
        if (res[0][i][j] != -1 && res[1][i][j] != -1 && res[0][i][j] + res[1][i][j] == 2*ans) {
          if (input[i][j] == '|') num[i][j] = 1
          else if (input[i][j] == '-') num[i][j] = 0
          else if (input[i][j] == 'L' || input[i][j] == 'J') num[i][j] = 1
        }  else if (input[i][j] == 'S') {
          num[i][j] = 1
        }
      }
    }

    var cnt = 0
    for (i in 0 until n) {
      var sum = 0
      for (j in 0 until m) {
        if (input[i][j] == '.' && sum%2 == 1) {
          cnt++
        }
        sum += num[i][j]
      }
    }

    return cnt

  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day10_test")
  check(part1(testInput) == 8)

  val input = readInput("Day10")
  part1(input).println()
  part2(input).println()
}
