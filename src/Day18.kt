import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {

  data class Segment(
    val x: Int,
    val y1: Int,
    val y2: Int,
  )

  fun part1(input: List<String>): Long {
    var nowX = 0
    var nowY = 0

    var area = 0L
    var perimeter = 0L

    for (line in input) {
      var dir = line.split(" ")[0][0]

      var num = line.split(" ")[1].toInt()

      perimeter += num

      if (dir == 'R') {
        area += nowX * (nowY + num) - nowX * nowY
        nowY += num
      } else if (dir == 'D') {
        area += nowX * nowY - (nowX + num) * nowY
        nowX += num
      } else if (dir == 'L') {
        area += nowX * (nowY - num) - nowX * nowY
        nowY -= num
      } else if (dir == 'U') {
        area += nowX * nowY - (nowX - num) * nowY
        nowX -= num
      }
    }

    var ans = abs(area)/2 - perimeter / 2 + 1 + perimeter


    return ans


  }


  fun part2(input: List<String>): Long {
    var nowX = 0L
    var nowY = 0L

    var area = 0L
    var perimeter = 0L

    for (line in input) {
      val color = line.substringAfter("(").substringBefore(")")

      var dir = when (color.last()) {
        '0' -> 'R'
        '1' -> 'D'
        '2' -> 'L'
        '3' -> 'U'
        else -> ' '
      }


      var num = color.substring(1, 6).toInt(16)

      perimeter += num

      if (dir == 'R') {
        area += nowX * (nowY + num) - nowX * nowY
        nowY += num
      } else if (dir == 'D') {
        area += nowX * nowY - (nowX + num) * nowY
        nowX += num
      } else if (dir == 'L') {
        area += nowX * (nowY - num) - nowX * nowY
        nowY -= num
      } else if (dir == 'U') {
        area += nowX * nowY - (nowX - num) * nowY
        nowX -= num
      }
    }

    var ans = abs(area)/2 - perimeter / 2 + 1 + perimeter


    return ans


    // var nowX = 0
    // var nowY = 0
    //
    // val segments = mutableListOf<Segment>()
    //
    // var borden = 0L
    //
    // for (line in input) {
    //   val color = line.substringAfter("(").substringBefore(")")
    //
    //   var dir = when(color.last()) {
    //     '0' -> 'R'
    //     '1' -> 'D'
    //     '2' -> 'L'
    //     '3' -> 'U'
    //     else -> ' '
    //   }
    //
    //
    //   var num = color.substring(1, 6).toInt(16)
    //
    //   borden += num
    //
    //   // println("num: $num, dir: $dir")
    //
    //   if (dir == 'R') {
    //     segments.add(Segment(nowX, nowY, nowY+num))
    //     nowY += num
    //   } else if (dir == 'D') {
    //     nowX += num
    //   } else if (dir == 'L') {
    //     segments.add(Segment(nowX, nowY-num, nowY))
    //     nowY -= num
    //   } else if (dir == 'U') {
    //     nowX -= num
    //   }
    // }
    //
    // println(segments)
    //
    // val segmentsByX = segments.groupBy { it.x }
    //
    // val allX = segmentsByX.keys.toList().sorted()
    //
    // println("size of allX: ${allX.size}")
    //
    // var ans = 0L
    //
    // for (i in 1 until allX.size) {
    //
    //   var height = allX[i] - allX[i-1]
    //   // if (i+1 == allX.size) height++
    //
    //   val lines = mutableListOf<Pair<Int, Int>>()
    //
    //   val set = mutableSetOf<Int>()
    //
    //   for (j in 0 until i) {
    //     for (seg in segmentsByX.get(allX[j])!!) {
    //       set.add(seg.y1)
    //       set.add(seg.y2)
    //
    //       lines.add(seg.y1 to seg.y2)
    //     }
    //   }
    //
    //   for (seg in segmentsByX.get(allX[i])!!) {
    //     set.add(seg.y1)
    //     set.add(seg.y2)
    //   }
    //
    //   val listOfX = set.toList().sorted()
    //
    //   var width = 0
    //   for (j in 1 until listOfX.size) {
    //     if (lines.count { listOfX[j-1] >= it.first && listOfX[j-1] < it.second } %2 == 1) {
    //         width = listOfX[j] - listOfX[j-1]
    //       if (j+1 >= listOfX.size || lines.count { listOfX[j] >= it.first && listOfX[j] < it.second } %2 == 0) {
    //         width++
    //       }
    //
    //       if (i+1 == allX.size || segmentsByX.get(allX[i])!!.any { listOfX[j-1] >= it.y1 && listOfX[j] <= it.y2 }) {
    //         ans += 1L * width * (height + 1)
    //       } else {
    //         ans += 1L * width * height
    //       }
    //
    //     }
    //   }
    //
    //   // ans += 1L * width * height
    //
    // }
    //
    //
    // return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day18_test")
  check(part1(testInput) == 62L)

  part2(testInput).println()


  val input = readInput("Day18")
  part1(input).println()
  part2(input).println()
}
