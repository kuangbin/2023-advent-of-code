fun main() {

  fun getNorthMap(mp: List<String>): Long {
    val n = mp.size
    val m = mp[0].length
    val ret = MutableList(0) { MutableList(0) { ' ' } }
    for (i in 0 until n) {
      ret.add(mp[i].toCharArray().toMutableList())
    }
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (ret[i][j] != 'O') {
          continue
        }
        for (k in i - 1 downTo 0) {
          if (ret[k][j] == '#' || ret[k][j] == 'O') {
            break
          }
          val tmp = ret[k][j]
          ret[k][j] = ret[k + 1][j]
          ret[k + 1][j] = tmp
        }
      }
    }

    var ans = 0L
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (ret[i][j] == 'O') {
          ans += n - i
        }
      }
    }

    println(ans)

    return ans
  }

  fun part1(input: List<String>): Long {
    return getNorthMap(input)
  }


  fun goNorth(mp: List<String>): List<String> {
    val n = mp.size
    val m = mp[0].length
    val ret = MutableList(0) { MutableList(0) { ' ' } }
    for (i in 0 until n) {
      ret.add(mp[i].toCharArray().toMutableList())
    }
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (ret[i][j] != 'O') {
          continue
        }
        for (k in i - 1 downTo 0) {
          if (ret[k][j] == '#' || ret[k][j] == 'O') {
            break
          }
          val tmp = ret[k][j]
          ret[k][j] = ret[k + 1][j]
          ret[k + 1][j] = tmp
        }
      }
    }



    return buildList {
      for (i in 0 until n) {
        add(String(ret[i].toCharArray()))
      }
    }
  }

  fun goWest(mp: List<String>): List<String> {
    val n = mp.size
    val m = mp[0].length
    val ret = MutableList(0) { MutableList(0) { ' ' } }
    for (i in 0 until n) {
      ret.add(mp[i].toCharArray().toMutableList())
    }
    for (j in 0 until m) {
      for (i in 0 until n) {
        if (ret[i][j] != 'O') {
          continue
        }
        for (k in j - 1 downTo 0) {
          if (ret[i][k] == '#' || ret[i][k] == 'O') {
            break
          }
          val tmp = ret[i][k]
          ret[i][k] = ret[i][k + 1]
          ret[i][k + 1] = tmp
        }
      }
    }

    return buildList {
      for (i in 0 until n) {
        add(String(ret[i].toCharArray()))
      }
    }
  }

  fun goSouth(mp: List<String>): List<String> {
    val n = mp.size
    val m = mp[0].length
    val ret = MutableList(0) { MutableList(0) { ' ' } }
    for (i in 0 until n) {
      ret.add(mp[i].toCharArray().toMutableList())
    }
    for (i in n - 1 downTo 0) {
      for (j in 0 until m) {
        if (ret[i][j] != 'O') {
          continue
        }
        for (k in i + 1 until n) {
          if (ret[k][j] == '#' || ret[k][j] == 'O') {
            break
          }
          val tmp = ret[k][j]
          ret[k][j] = ret[k - 1][j]
          ret[k - 1][j] = tmp
        }
      }
    }



    return buildList {
      for (i in 0 until n) {
        add(String(ret[i].toCharArray()))
      }
    }
  }

  fun goEast(mp: List<String>): List<String> {
    val n = mp.size
    val m = mp[0].length
    val ret = MutableList(0) { MutableList(0) { ' ' } }
    for (i in 0 until n) {
      ret.add(mp[i].toCharArray().toMutableList())
    }
    for (j in m - 1 downTo 0) {
      for (i in 0 until n) {
        if (ret[i][j] != 'O') {
          continue
        }
        for (k in j + 1 until m) {
          if (ret[i][k] == '#' || ret[i][k] == 'O') {
            break
          }
          val tmp = ret[i][k]
          ret[i][k] = ret[i][k - 1]
          ret[i][k - 1] = tmp
        }
      }
    }

    return buildList {
      for (i in 0 until n) {
        add(String(ret[i].toCharArray()))
      }
    }
  }

  fun List<String>.print() {
    for (i in 0 until this.size) {
      kotlin.io.println(this[i])
    }
  }

  fun oneRount(mp: List<String>): List<String> {
    val tmp1 = goNorth(mp)
    val tmp2 = goWest(mp)
    val tmp3 = goSouth(mp)
    val tmp4 = goEast(mp)
    return goEast(goSouth(goWest(goNorth(mp))))
  }

  fun listEqual(a: List<String>, b: List<String>): Boolean {
    return a.joinToString() == b.joinToString()
  }

  fun getAns(mp: List<String>): Long {
    val n = mp.size
    val m = mp[0].length
    var ans = 0L
    for (i in 0 until n) {
      for (j in 0 until m) {
        if (mp[i][j] == 'O') {
          ans += n - i
        }
      }
    }

    return ans
  }

  fun part2(input: List<String>): Long {
    var ans = 0

    var list = mutableListOf<List<String>>()
    list.add(input)
    for (i in 0..1000) {
      list.add(oneRount(list[i]))
    }
    for (i in 0..1000) {
      for (j in i+1..1000) {
        if (listEqual(list[i], list[j])) {
          println("Equal between $i and $j")
        }
      }
    }

    println(getAns(list[1000-36]))

    return getAns(list[1000])
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day14_test")
  check(part1(testInput) == 136L)

  val input = readInput("Day14")
  part1(input).println()
  part2(input).println()
}
