import java.time.ZonedDateTime
import java.util.Date

fun main() {
  val dateTimeInString = "2023-12-02T19:27:54+08:00"
  val zdt = ZonedDateTime.parse(dateTimeInString)
  val date = Date.from(zdt.toInstant())
  println(date)
}