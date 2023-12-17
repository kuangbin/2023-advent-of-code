import java.util.LinkedList
import java.util.Queue
import kotlin.math.min


const val INF = 0x3f3f3f3f

fun main() {
  data class Node(
    val x: Int,
    val y: Int,
    val dir: Int,
    val num: Int,
  )


  fun SPFA(mp: List<String>): Int {

    val n = mp.size
    val m = mp[0].length

    val dist = MutableList(n) { MutableList(m) {MutableList(4) { MutableList(4) {INF} } } }
    val vis = MutableList(n) { MutableList(m) {MutableList(4) { MutableList(4) {false} } } }

    val queue: Queue<Node> = LinkedList()

    dist[0][0][0][0] = 0
    dist[0][0][1][0] = 0
    queue.add(Node(0, 0, 0, 0))
    queue.add(Node(0, 0, 1, 0))
    vis[0][0][0][0] = true
    vis[0][0][1][0] = true

    while (queue.isNotEmpty()) {
      val now = queue.remove()

      val x = now.x
      val y = now.y
      val dir = now.dir
      val num = now.num
      vis[x][y][dir][num] = false

      if (dir == 0) {
        if (y+1 < m && num < 3) {
          if (dist[x][y+1][dir][num+1] > dist[x][y][dir][num] + (mp[x][y+1] - '0')) {
            dist[x][y+1][dir][num+1] = dist[x][y][dir][num] + (mp[x][y+1] - '0')
            if (!vis[x][y+1][dir][num+1]) {
              vis[x][y+1][dir][num+1] = true
              queue.add(Node(x, y+1, dir, num+1))
            }
          }
        }
        if (x-1 >= 0) {
          if (dist[x-1][y][3][1] > dist[x][y][dir][num] + (mp[x-1][y] - '0')) {
            dist[x-1][y][3][1] = dist[x][y][dir][num] + (mp[x-1][y] - '0')
            if (!vis[x-1][y][3][1]) {
              vis[x-1][y][3][1] = true
              queue.add(Node(x-1, y, 3, 1))
            }
          }
        }
        if (x+1 < n) {
          if (dist[x+1][y][1][1] > dist[x][y][dir][num] + (mp[x+1][y] - '0')) {
            dist[x+1][y][1][1] = dist[x][y][dir][num] + (mp[x+1][y] - '0')
            if (!vis[x+1][y][1][1]) {
              vis[x+1][y][1][1] = true
              queue.add(Node(x+1, y, 1, 1))
            }
          }
        }
      } else if (dir == 1) {
        if (x+1 < n && num < 3) {

          if (dist[x+1][y][dir][num+1] > dist[x][y][dir][num] + (mp[x+1][y] - '0')) {
            dist[x+1][y][dir][num+1] = dist[x][y][dir][num] + (mp[x+1][y] - '0')
            if (!vis[x+1][y][dir][num+1]) {
              vis[x+1][y][dir][num+1] = true
              queue.add(Node(x+1, y, dir, num+1))
            }
          }
        }
        if (y-1 >= 0) {
          if (dist[x][y-1][2][1] > dist[x][y][dir][num] + (mp[x][y-1] - '0')) {
            dist[x][y-1][2][1] = dist[x][y][dir][num] + (mp[x][y-1] - '0')
            if (!vis[x][y-1][2][1]) {
              vis[x][y-1][2][1] = true
              queue.add(Node(x, y-1, 2, 1))
            }
          }
        }
        if (y+1 < m) {
          if (dist[x][y+1][0][1] > dist[x][y][dir][num] + (mp[x][y+1] - '0')) {
            dist[x][y+1][0][1] = dist[x][y][dir][num] + (mp[x][y+1] - '0')
            if (!vis[x][y+1][0][1]) {
              vis[x][y+1][0][1] = true
              queue.add(Node(x, y+1, 0, 1))
            }
          }
        }
      } else if (dir == 2) {
        if (y-1 >= 0 && num < 3) {
          if (dist[x][y-1][dir][num+1] > dist[x][y][dir][num] + (mp[x][y-1] - '0')) {
            dist[x][y-1][dir][num+1] = dist[x][y][dir][num] + (mp[x][y-1] - '0')
            if (!vis[x][y-1][dir][num+1]) {
              vis[x][y-1][dir][num+1] = true
              queue.add(Node(x, y-1, dir, num+1))
            }
          }
        }
        if (x-1 >= 0) {
          if (dist[x-1][y][3][1] > dist[x][y][dir][num] + (mp[x-1][y] - '0')) {
            dist[x-1][y][3][1] = dist[x][y][dir][num] + (mp[x-1][y] - '0')
            if (!vis[x-1][y][3][1]) {
              vis[x-1][y][3][1] = true
              queue.add(Node(x-1, y, 3, 1))
            }
          }
        }
        if (x+1 < n) {
          if (dist[x+1][y][1][1] > dist[x][y][dir][num] + (mp[x+1][y] - '0')) {
            dist[x+1][y][1][1] = dist[x][y][dir][num] + (mp[x+1][y] - '0')
            if (!vis[x+1][y][1][1]) {
              vis[x+1][y][1][1] = true
              queue.add(Node(x+1, y, 1, 1))
            }
          }
        }
      } else if (dir == 3) {
        if (x-1 >= 0 && num < 3) {
          if (dist[x-1][y][dir][num+1] > dist[x][y][dir][num] + (mp[x-1][y] - '0')) {
            dist[x-1][y][dir][num+1] = dist[x][y][dir][num] + (mp[x-1][y] - '0')
            if (!vis[x-1][y][dir][num+1]) {
              vis[x-1][y][dir][num+1] = true
              queue.add(Node(x-1, y, dir, num+1))
            }
          }
        }
        if (y-1 >= 0) {
          if (dist[x][y-1][2][1] > dist[x][y][dir][num] + (mp[x][y-1] - '0')) {
            dist[x][y-1][2][1] = dist[x][y][dir][num] + (mp[x][y-1] - '0')
            if (!vis[x][y-1][2][1]) {
              vis[x][y-1][2][1] = true
              queue.add(Node(x, y-1, 2, 1))
            }
          }
        }
        if (y+1 < m) {
          if (dist[x][y+1][0][1] > dist[x][y][dir][num] + (mp[x][y+1] - '0')) {
            dist[x][y+1][0][1] = dist[x][y][dir][num] + (mp[x][y+1] - '0')
            if (!vis[x][y+1][0][1]) {
              vis[x][y+1][0][1] = true
              queue.add(Node(x, y+1, 0, 1))
            }
          }
        }
      }
    }

    var ans = INF

    for (i in 0 until 4) {
      for (j in 0 until 4) {
        ans = min(ans, dist[n-1][m-1][i][j])
      }
    }
    return ans
  }


  fun part1(input: List<String>): Int {
    return SPFA(input)
  }


  fun SPFA2(mp: List<String>): Int {

    val n = mp.size
    val m = mp[0].length

    val dist = MutableList(n) { MutableList(m) {MutableList(4) { MutableList(11) {INF} } } }
    val vis = MutableList(n) { MutableList(m) {MutableList(4) { MutableList(11) {false} } } }

    val queue: Queue<Node> = LinkedList()

    dist[0][0][0][0] = 0
    dist[0][0][1][0] = 0
    queue.add(Node(0, 0, 0, 0))
    queue.add(Node(0, 0, 1, 0))
    vis[0][0][0][0] = true
    vis[0][0][1][0] = true

    while (queue.isNotEmpty()) {
      val now = queue.remove()

      val x = now.x
      val y = now.y
      val dir = now.dir
      val num = now.num
      vis[x][y][dir][num] = false

      if (dir == 0) {
        if (y+1 < m && num < 10) {
          if (dist[x][y+1][dir][num+1] > dist[x][y][dir][num] + (mp[x][y+1] - '0')) {
            dist[x][y+1][dir][num+1] = dist[x][y][dir][num] + (mp[x][y+1] - '0')
            if (!vis[x][y+1][dir][num+1]) {
              vis[x][y+1][dir][num+1] = true
              queue.add(Node(x, y+1, dir, num+1))
            }
          }
        }
        if (x-1 >= 0 && num >= 4) {
          if (dist[x-1][y][3][1] > dist[x][y][dir][num] + (mp[x-1][y] - '0')) {
            dist[x-1][y][3][1] = dist[x][y][dir][num] + (mp[x-1][y] - '0')
            if (!vis[x-1][y][3][1]) {
              vis[x-1][y][3][1] = true
              queue.add(Node(x-1, y, 3, 1))
            }
          }
        }
        if (x+1 < n && num >= 4) {
          if (dist[x+1][y][1][1] > dist[x][y][dir][num] + (mp[x+1][y] - '0')) {
            dist[x+1][y][1][1] = dist[x][y][dir][num] + (mp[x+1][y] - '0')
            if (!vis[x+1][y][1][1]) {
              vis[x+1][y][1][1] = true
              queue.add(Node(x+1, y, 1, 1))
            }
          }
        }
      } else if (dir == 1) {
        if (x+1 < n && num < 10) {

          if (dist[x+1][y][dir][num+1] > dist[x][y][dir][num] + (mp[x+1][y] - '0')) {
            dist[x+1][y][dir][num+1] = dist[x][y][dir][num] + (mp[x+1][y] - '0')
            if (!vis[x+1][y][dir][num+1]) {
              vis[x+1][y][dir][num+1] = true
              queue.add(Node(x+1, y, dir, num+1))
            }
          }
        }
        if (y-1 >= 0 && num >= 4) {
          if (dist[x][y-1][2][1] > dist[x][y][dir][num] + (mp[x][y-1] - '0')) {
            dist[x][y-1][2][1] = dist[x][y][dir][num] + (mp[x][y-1] - '0')
            if (!vis[x][y-1][2][1]) {
              vis[x][y-1][2][1] = true
              queue.add(Node(x, y-1, 2, 1))
            }
          }
        }
        if (y+1 < m && num >= 4) {
          if (dist[x][y+1][0][1] > dist[x][y][dir][num] + (mp[x][y+1] - '0')) {
            dist[x][y+1][0][1] = dist[x][y][dir][num] + (mp[x][y+1] - '0')
            if (!vis[x][y+1][0][1]) {
              vis[x][y+1][0][1] = true
              queue.add(Node(x, y+1, 0, 1))
            }
          }
        }
      } else if (dir == 2) {
        if (y-1 >= 0 && num < 10) {
          if (dist[x][y-1][dir][num+1] > dist[x][y][dir][num] + (mp[x][y-1] - '0')) {
            dist[x][y-1][dir][num+1] = dist[x][y][dir][num] + (mp[x][y-1] - '0')
            if (!vis[x][y-1][dir][num+1]) {
              vis[x][y-1][dir][num+1] = true
              queue.add(Node(x, y-1, dir, num+1))
            }
          }
        }
        if (x-1 >= 0 && num >= 4) {
          if (dist[x-1][y][3][1] > dist[x][y][dir][num] + (mp[x-1][y] - '0')) {
            dist[x-1][y][3][1] = dist[x][y][dir][num] + (mp[x-1][y] - '0')
            if (!vis[x-1][y][3][1]) {
              vis[x-1][y][3][1] = true
              queue.add(Node(x-1, y, 3, 1))
            }
          }
        }
        if (x+1 < n && num >= 4) {
          if (dist[x+1][y][1][1] > dist[x][y][dir][num] + (mp[x+1][y] - '0')) {
            dist[x+1][y][1][1] = dist[x][y][dir][num] + (mp[x+1][y] - '0')
            if (!vis[x+1][y][1][1]) {
              vis[x+1][y][1][1] = true
              queue.add(Node(x+1, y, 1, 1))
            }
          }
        }
      } else if (dir == 3) {
        if (x-1 >= 0 && num < 10) {
          if (dist[x-1][y][dir][num+1] > dist[x][y][dir][num] + (mp[x-1][y] - '0')) {
            dist[x-1][y][dir][num+1] = dist[x][y][dir][num] + (mp[x-1][y] - '0')
            if (!vis[x-1][y][dir][num+1]) {
              vis[x-1][y][dir][num+1] = true
              queue.add(Node(x-1, y, dir, num+1))
            }
          }
        }
        if (y-1 >= 0 && num >= 4) {
          if (dist[x][y-1][2][1] > dist[x][y][dir][num] + (mp[x][y-1] - '0')) {
            dist[x][y-1][2][1] = dist[x][y][dir][num] + (mp[x][y-1] - '0')
            if (!vis[x][y-1][2][1]) {
              vis[x][y-1][2][1] = true
              queue.add(Node(x, y-1, 2, 1))
            }
          }
        }
        if (y+1 < m && num >= 4) {
          if (dist[x][y+1][0][1] > dist[x][y][dir][num] + (mp[x][y+1] - '0')) {
            dist[x][y+1][0][1] = dist[x][y][dir][num] + (mp[x][y+1] - '0')
            if (!vis[x][y+1][0][1]) {
              vis[x][y+1][0][1] = true
              queue.add(Node(x, y+1, 0, 1))
            }
          }
        }
      }
    }

    var ans = INF

    for (i in 0 until 4) {
      for (j in 0 until 11) {
        ans = min(ans, dist[n-1][m-1][i][j])
      }
    }
    return ans
  }


  fun part2(input: List<String>): Int {
    var ans = 0

    return SPFA2(input)
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day17_test")
  check(part1(testInput) == 102)
  part2(testInput).println()

  val input = readInput("Day17")
  part1(input).println()
  part2(input).println()
}
