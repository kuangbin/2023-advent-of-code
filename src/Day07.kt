import kotlin.math.min

fun main() {

  fun getRank(c: Char): Int {
    return when (c) {
      'A' -> 14
      'K' -> 13
      'Q' -> 12
      'J' -> 11
      'T' -> 10
      else -> c - '0'
    }
  }

  fun getRank2(c: Char): Int {
    return when (c) {
      'A' -> 14
      'K' -> 13
      'Q' -> 12
      'J' -> 1
      'T' -> 10
      else -> c - '0'
    }
  }

  fun getPresentation(cards: String): List<Pair<Int, Int>> {
    val cnt = mutableMapOf<Int, Int>()
    for (card in cards.toCharArray()) {
        cnt.put(getRank(card), cnt.getOrDefault(getRank(card), 0) + 1)
    }
    val list = mutableListOf<Pair<Int, Int>>()
    for (i in 2..14) {
      if (cnt.containsKey(i)) {
        list.add(cnt[i]!! to i)
      }
    }
    return list.sortedBy { -(it.first * 10000 + it.second) }
  }

  fun getPresentation2(cards: String): List<Pair<Int, Int>> {
    val cnt = mutableMapOf<Int, Int>()
    var numJ = 0
    for (card in cards.toCharArray()) {
      if (card == 'J') {
        numJ++
        continue
      }
      cnt.put(getRank(card), cnt.getOrDefault(getRank(card), 0) + 1)
    }

    var now = 1
    var maxN = 0
    for (i in 14 downTo 2) {
      if (cnt.getOrDefault(i, 0) > maxN) {
        now = i
        maxN = cnt.getOrDefault(i, 0)
      }
    }

    cnt.put(now, maxN + numJ)

    val list = mutableListOf<Pair<Int, Int>>()
    for (i in 1..14) {
      if (cnt.containsKey(i)) {
        list.add(cnt[i]!! to i)
      }
    }
    return list.sortedBy { -(it.first * 10000 + it.second) }
  }

  fun compare(card1: String, card2: String): Int {
    val list1 = card1.toCharArray().map { getRank(it) }
    val list2 = card2.toCharArray().map { getRank(it) }
    for (i in 0 until min(list1.size, list2.size)) {
      if (list1[i] != list2[i]) {
        return if (list1[i] < list2[i]) -1 else 1
      }
    }
    return 0
  }

  fun compare2(card1: String, card2: String): Int {
    val list1 = card1.toCharArray().map { getRank2(it) }
    val list2 = card2.toCharArray().map { getRank2(it) }
    for (i in 0 until min(list1.size, list2.size)) {
      if (list1[i] != list2[i]) {
        return if (list1[i] < list2[i]) -1 else 1
      }
    }
    return 0
  }

  fun getType(cards: String): Int {
    val list = getPresentation(cards)
    var c5 = 0
    var c4 = 0
    var c3 = 0
    var c2 = 0
    for (ll in list) {
      if (ll.first == 5) c5++
      else if (ll.first == 4) c4++
      else if (ll.first == 3) c3++
      else if (ll.first == 2) c2++
    }

    if (c5 == 1) return 7
    if (c4 == 1) return 6
    if (c3 == 1 && c2 == 1) return 5
    if (c3 == 1) return 4
    if (c2 == 2) return 3
    if (c2 == 1) return 2
    return 1
  }

  fun getType2(cards: String): Int {
    val list = getPresentation2(cards)
    var c5 = 0
    var c4 = 0
    var c3 = 0
    var c2 = 0
    for (ll in list) {
      if (ll.first == 5) c5++
      else if (ll.first == 4) c4++
      else if (ll.first == 3) c3++
      else if (ll.first == 2) c2++
    }

    if (c5 == 1) return 7
    if (c4 == 1) return 6
    if (c3 == 1 && c2 == 1) return 5
    if (c3 == 1) return 4
    if (c2 == 2) return 3
    if (c2 == 1) return 2
    return 1
  }

  fun part1(input: List<String>): Long {
    var list = mutableListOf<Pair<String, Int>>()
    for (text in input) {
      val cards = text.split(" ")[0]
      val num = text.split(" ")[1].toInt()
      list.add(cards to num)
    }
    val comparator = compareBy<Pair<String, Int>> { getType(it.first) }.thenComparator { a, b ->
      compare(a.first, b.first)
    }
    list.sortWith(comparator)
    println(list)

    var ans = 0L
    for ((index, value) in list.withIndex()) {
      ans += 1L * (index+1) * value.second

    }

    return ans
  }

  fun part2(input: List<String>): Long {
    var list = mutableListOf<Pair<String, Int>>()
    for (text in input) {
      val cards = text.split(" ")[0]
      val num = text.split(" ")[1].toInt()
      list.add(cards to num)
    }
    val comparator = compareBy<Pair<String, Int>> { getType2(it.first) }.thenComparator { a, b ->
      compare2(a.first, b.first)
    }
    list.sortWith(comparator)
    println(list)

    var ans = 0L
    for ((index, value) in list.withIndex()) {
      ans += 1L * (index+1) * value.second

    }

    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day07_test")
  check(part1(testInput) == 6440L)
  println(part2(testInput))

  val input = readInput("Day07")
  part1(input).println()
  part2(input).println()
}
