import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

data class Node(
  val x: Int,
  val y: Int,
  val dir: Int,
)
fun main() {
  fun getRes(input: List<String>, sx: Int, sy: Int, sDir: Int): Int {


    val n = input.size
    val m = input[0].length

    val queue: Queue<Node> = LinkedList()
    val visited = MutableList(n) { MutableList(m) { MutableList(4) {false} } }

    queue.add(Node(sx, sy, sDir))

    while (queue.isNotEmpty()) {
      val now = queue.remove()
      val x = now.x
      val y = now.y
      val dir = now.dir
      if (dir == 0) {
        // Right
        if (y+1 >= m) continue
        if (visited[x][y+1][dir]) continue
        visited[x][y+1][dir] = true
        if (input[x][y+1] == '.' || input[x][y+1] == '-') {
          queue.add(Node(x, y+1, dir))
        } else if (input[x][y+1] == '|') {
          // visited[x][y+1][1] = true
          // visited[x][y+1][3] = true
          queue.add(Node(x, y+1, 1))
          queue.add(Node(x, y+1, 3))
        } else if (input[x][y+1] == '/') {
          // visited[x][y+1][3] = true
          queue.add(Node(x, y+1, 3))
        } else if (input[x][y+1] == '\\') {
          // visited[x][y+1][1] = true
          queue.add(Node(x, y+1, 1))
        }
      } else if (dir == 1) {
        // Down
        if (x+1 >= n) continue
        if (visited[x+1][y][dir]) continue
        visited[x+1][y][dir] = true
        if (input[x+1][y] == '.' || input[x+1][y] == '|') {
          queue.add(Node(x+1, y, dir))
        } else if (input[x+1][y] == '-') {
          // visited[x+1][y][0] = true
          queue.add(Node(x+1, y, 0))
          // visited[x+1][y][2] = true
          queue.add(Node(x+1, y, 2))
        } else if (input[x+1][y] == '/') {
          // visited[x+1][y][2] = true
          queue.add(Node(x+1, y, 2))
        } else if (input[x+1][y] == '\\') {
          // visited[x+1][y][0] = true
          queue.add(Node(x+1, y, 0))
        }
      } else if (dir == 2) {
        // Left
        if (y-1 < 0) continue
        if (visited[x][y-1][dir]) continue
        visited[x][y-1][dir] = true
        if (input[x][y-1] == '.' || input[x][y-1] == '-') {
          queue.add(Node(x, y-1, dir))
        } else if (input[x][y-1] == '|') {
          // visited[x][y-1][1] = true
          // visited[x][y-1][3] = true
          queue.add(Node(x, y-1, 1))
          queue.add(Node(x, y-1, 3))
        } else if (input[x][y-1] == '\\') {
          // visited[x][y-1][3] = true
          queue.add(Node(x, y-1, 3))
        } else if (input[x][y-1] == '/') {
          // visited[x][y-1][1] = true
          queue.add(Node(x, y-1, 1))
        }

      } else if (dir == 3) {
        // Up

        if (x-1 < 0) continue
        if (visited[x-1][y][dir]) continue
        visited[x-1][y][dir] = true
        if (input[x-1][y] == '.' || input[x-1][y] == '|') {
          queue.add(Node(x-1, y, dir))
        } else if (input[x-1][y] == '-') {
          // visited[x-1][y][0] = true
          queue.add(Node(x-1, y, 0))
          // visited[x-1][y][2] = true
          queue.add(Node(x-1, y, 2))
        } else if (input[x-1][y] == '\\') {
          // visited[x-1][y][2] = true
          queue.add(Node(x-1, y, 2))
        } else if (input[x-1][y] == '/') {
          // visited[x-1][y][0] = true
          queue.add(Node(x-1, y, 0))
        }
      }
    }

    var ans = 0
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (visited[i][j].any { it }) {
          ans++
        }

      }
    }


    return ans
  }


  fun part1(input: List<String>): Int {

    return getRes(input, 0, -1, 0)
  }

  fun part2(input: List<String>): Int {
    var ans = 0
    val n = input.size
    val m = input[0].length

    for (i in 0 until n) {
      ans = max(ans, getRes(input, 0, -1, 0))
      ans = max(ans, getRes(input, 0, m, 2))
    }

    for (j in 0 until m) {
      ans = max(ans, getRes(input, -1, j, 1))
      ans = max(ans, getRes(input, n, j, 3))
    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day16_test")
  check(part1(testInput) == 46)
  part2(testInput).println()

  val input = readInput("Day16")
  part1(input).println()
  part2(input).println()
}
