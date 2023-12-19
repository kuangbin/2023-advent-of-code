import kotlin.math.max
import kotlin.math.min

fun main() {
  fun gao(map: Map<String, List<String>>, values: Map<String, Int>, now: String): Boolean {

    val rules = map[now]!!
    for (rule in rules) {
      if (rule == "A") return true
      if (rule == "R") return false
      if (rule.contains(":")) {
        val condition = rule.substringBefore(":")
        val result = rule.substringAfter(":")
        if (condition.contains(">")) {
          if (values[condition.substringBefore(">")]!! > condition.substringAfter(">").toInt()) {
            if (result == "A") return true
            if (result == "R") return false
            return gao(map, values, result)
          }
        }
        if (condition.contains("<")) {
          if (values[condition.substringBefore("<")]!! < condition.substringAfter("<").toInt()) {
            if (result == "A") return true
            if (result == "R") return false
            return gao(map, values, result)
          }
        }
      } else {
        return gao(map, values, rule)
      }
    }
    return false
  }


  fun part1(input: List<String>): Long {

    val map = mutableMapOf<String, List<String>>()
    var i = 0
    while (i < input.size) {
      if (input[i].isEmpty()) {
        i++
        break
      }
      val name = input[i].substringBefore("{")
      val rules = input[i].substringAfter("{").substringBefore("}").split(",")
      map.put(name, rules)
      i++
    }

    var ans = 0L

    while (i < input.size) {
      val values =
        input[i].substringAfter("{").substringBefore("}").split(",").map {
          val name = it.substringBefore("=")
          val value = it.substringAfter("=").toInt()
          name to value
        }.toMap()

      if (gao(map, values, "in")) {
        ans += values.values.sum()
      }

      i++
    }


    return ans
  }


  fun getNumber(map: Map<String, List<String>>, originalValues: MutableMap<String, Pair<Int, Int>>, now: String): Long {


    val rules = map[now]!!

    var ans = 0L

    val values = buildMap {
      putAll(originalValues)
    }.toMutableMap()

    for (rule in rules) {
      if (rule == "A") {
        ans += values.values.map { (it.second - it.first + 1).toLong() }.reduce { acc, l -> acc * l  }
        break
      }
      if (rule == "R") {
        break
      }
      if (rule.contains(":")) {
        val condition = rule.substringBefore(":")
        val result = rule.substringAfter(":")
        if (condition.contains(">")) {
          if (values[condition.substringBefore(">")]!!.second > condition.substringAfter(">").toInt()) {
            val newValues = values.toMutableMap()
            val l = max(values[condition.substringBefore(">")]!!.first, condition.substringAfter(">").toInt() + 1)
            val h = values[condition.substringBefore(">")]!!.second
            newValues.put(condition.substringBefore(">"), l to h)

            if (result == "A") {
              ans += newValues.values.map { (it.second - it.first + 1).toLong() }.reduce { acc, l -> acc * l  }
            }
            else if (result == "R") {
            } else {
              ans += getNumber(map, newValues, result)
            }
          }

          if (values[condition.substringBefore(">")]!!.first <= condition.substringAfter(">").toInt()) {
            val l = values[condition.substringBefore(">")]!!.first
            val h = min(values[condition.substringBefore(">")]!!.second, condition.substringAfter(">").toInt())
            values.put(condition.substringBefore(">"), l to h)
          } else {
            break
          }
        }
        if (condition.contains("<")) {
          if (values[condition.substringBefore("<")]!!.first < condition.substringAfter("<").toInt()) {
            val newValues = values.toMutableMap()
            val l = values[condition.substringBefore("<")]!!.first
            val h = min(values[condition.substringBefore("<")]!!.second, condition.substringAfter("<").toInt() - 1)
            newValues.put(condition.substringBefore("<"), l to h)

            if (result == "A") {
              ans += newValues.values.map { (it.second - it.first + 1).toLong() }.reduce { acc, l -> acc * l  }
            }
            else if (result == "R") {
            } else {
              ans += getNumber(map, newValues, result)
            }
          }

          if (values[condition.substringBefore("<")]!!.second >= condition.substringAfter("<").toInt()) {
            val l = max(values[condition.substringBefore("<")]!!.first, condition.substringAfter("<").toInt())
            val h = values[condition.substringBefore("<")]!!.second
            values.put(condition.substringBefore("<"), l to h)
          } else {
            break
          }
        }
      } else {
        ans += getNumber(map, values, rule)
      }
    }
    return ans
  }

  fun part2(input: List<String>): Long {
    val map = mutableMapOf<String, List<String>>()
    var i = 0
    while (i < input.size) {
      if (input[i].isEmpty()) {
        i++
        break
      }
      val name = input[i].substringBefore("{")
      val rules = input[i].substringAfter("{").substringBefore("}").split(",")
      map.put(name, rules)
      i++
    }

    val values = mutableMapOf(
      "x" to (1 to 4000),
      "m" to (1 to 4000),
      "a" to (1 to 4000),
      "s" to (1 to 4000),
    )


    var ans = getNumber(map, values, "in")
    return ans
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day19_test")
  check(part1(testInput) == 19114L)

  part2(testInput).println()

  val input = readInput("Day19")
  part1(input).println()
  part2(input).println()
}
