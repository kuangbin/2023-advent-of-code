import kotlin.math.abs

fun main() {


  val moves = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
  fun isPart(maze: List<String>, x: Int, y: Int): Boolean {
    val n = maze.size
    val m = maze[0].length
    for (p in moves) {
      val xx = x + p.first
      val yy = y + p.second
      if (xx >= 0 && xx < n && yy >= 0 && yy < m) {
        if (maze[xx][yy] !in '0'..'9' && maze[xx][yy] != '.') {
          return true
        }
      }
    }
    return false
  }



  fun part1(input: List<String>): Long {
    val n = input.size
    val m = input[0].length
    var sum = 0L

    for (i in 0 until n) {
      var cur = 0
      var flag = false
      for (j in 0 until m) {
        if (input[i][j] !in '0'..'9') {
          if (cur != 0) {
            if (flag) sum += cur
          }
          cur = 0
          flag = false
        } else {
          cur = cur * 10 + (input[i][j] - '0')
          if (isPart(input, i, j)) {
            flag = true
          }
        }
      }
      if (cur != 0 && flag) {
        sum += cur
      }
    }

    return sum
  }

  fun getAdjacentNumbers(maze: List<String>, x: Int, y: Int): List<Int> {
    val ans = mutableListOf<Int>()

    val n = maze.size
    val m = maze[0].length
    for (p in moves) {
      val xx = x + p.first
      val yy = y + p.second
      if (xx >= 0 && xx < n && yy >= 0 && yy < m) {
        if (maze[xx][yy] in '0'..'9') {
          if (yy-1 >= 0 && abs(yy-1-y) <= 1 && maze[xx][yy-1] in '0'..'9') {
            continue
          }
          var st = yy
          var ed = yy
          while (st > 0 && maze[xx][st-1] in '0'..'9') st--
          while (ed < m-1 && maze[xx][ed+1] in '0'..'9') ed++
          var tmp = 0
          for (i in st..ed) {
            tmp = tmp * 10 + (maze[xx][i] - '0')
          }
          ans.add(tmp)
        }
      }
    }
    return ans
  }

  fun part2(input: List<String>): Long {
    val n = input.size
    val m = input[0].length
    var sum = 0L

    for (i in 0 until n) {
      for (j in 0 until m) {
        if (input[i][j] == '*') {
          val ll = getAdjacentNumbers(input, i, j)
          if (ll.size == 2) {
            println("${ll[0]} ${ll[1]}")
            sum += ll[0] * ll[1]
          }
        }
      }
    }

    return sum
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day03_test")
  check(part1(testInput) == 4361L)
  println(part2(testInput))

  val input = readInput("Day03")
  part1(input).println()
  part2(input).println()
}
