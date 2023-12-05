import kotlin.math.max
import kotlin.math.min

data class Range(
  val src: Long,
  val tgt: Long,
  val len: Long,
)
fun main() {
  fun getReverseMapping(mapping: List<Range>, now: Long): Long {
    for (range in mapping) {
      if (now in range.tgt..range.tgt+range.len-1) {
        return range.src + (now - range.tgt)
      }
    }
    return now
  }

  fun getNewSet(mapping: List<Range>, set: Set<Long>): Set<Long> {
    val ans = mutableSetOf<Long>()

    for (i in set) {
      ans.add(getReverseMapping(mapping, i))
    }

    for (range in mapping) {
      ans.add(range.src)
      ans.add(range.src+range.len)
    }

    return ans
  }

  fun getMapping(mapping: List<Range>, now: Long): Long {
    for (range in mapping) {
      if (now in range.src..range.src+range.len-1) {
        return range.tgt + (now - range.src)
      }
    }
    return now
  }
  fun getLocation(mapping: List<List<Range>>, seed: Long): Long {
    var ans = seed
    for (mp in mapping) {
      ans = getMapping(mp, ans)
    }
    return ans
  }

  fun part1(input: List<String>): Long {

    val seeds = input[0].substringAfter(": ").trim().split(" ")
      .map { it.trim().toLong() }
    println(seeds)

    val mapping = mutableListOf<List<Range>>()
    var cur = mutableListOf<Range>()
    for (i in 1 until input.size) {
      if (input[i].isEmpty()) continue
      if (input[i][0] !in '0'..'9') {
        if (cur.isNotEmpty()) {
          mapping.add(cur.toList())
          cur.clear()
        }
        continue
      }
      val nums = input[i].split(" ").map { it.trim().toLong() }
      cur.add(Range(nums[1], nums[0], nums[2]))
    }
    if (cur.isNotEmpty()) {
      mapping.add(cur.toList())
      cur.clear()
    }

    println(mapping)

    var ans = getLocation(mapping, seeds[0])
    for (seed in seeds) {
      ans = min(ans, getLocation(mapping, seed))
    }

    return ans
  }

  fun part2(input: List<String>): Long {

    val seeds = input[0].substringAfter(": ").trim().split(" ")
      .map { it.trim().toLong() }
    println(seeds)

    val seedPairs = mutableListOf<Pair<Long, Long>>()
    for (i in 0 until seeds.size step 2) {
      seedPairs.add(seeds[i] to seeds[i+1])
    }

    val mapping = mutableListOf<List<Range>>()
    var cur = mutableListOf<Range>()
    for (i in 1 until input.size) {
      if (input[i].isEmpty()) continue
      if (input[i][0] !in '0'..'9') {
        if (cur.isNotEmpty()) {
          mapping.add(cur.toList())
          cur.clear()
        }
        continue
      }
      val nums = input[i].split(" ").map { it.trim().toLong() }
      cur.add(Range(nums[1], nums[0], nums[2]))
    }
    if (cur.isNotEmpty()) {
      mapping.add(cur.toList())
      cur.clear()
    }

    println(mapping)

    var nowSet = setOf(1L)
    for (mp in mapping.reversed()) {
      nowSet = getNewSet(mp, nowSet)
    }

    var ans = getLocation(mapping, seedPairs[0].first)
    for (pp in seedPairs) {
      ans = min(ans, getLocation(mapping, pp.first))
    }
    for (tmp in nowSet) {
      if (seedPairs.any { tmp in it.first until it.first+it.second }) {
        ans = min(ans, getLocation(mapping, tmp))
      }
    }

    return ans

  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day05_test")
  check(part1(testInput) == 35L)
  println(part2(testInput))

  val input = readInput("Day05")
  part1(input).println()
  part2(input).println()
}
