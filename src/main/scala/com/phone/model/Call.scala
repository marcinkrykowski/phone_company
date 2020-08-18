package com.phone.model

case class Call(
    customerId: String,
    numberCalled: String,
    durationInSeconds: Int
) {

  val costPerMinute: BigDecimal = 0.05
  val costPerMinuteExceeded: BigDecimal = 0.03
  val threeMinutesInSeconds: BigDecimal = 180
  val amountAfterThreeMinutes: BigDecimal =
    costPerMinute * threeMinutesInSeconds

  def cost: BigDecimal = {
    if (durationInSeconds <= threeMinutesInSeconds) {
      durationInSeconds * costPerMinute
    } else {
      amountAfterThreeMinutes + (durationInSeconds - threeMinutesInSeconds) * costPerMinuteExceeded
    }
  }
}

object Call {
  def apply(callString: String): Call = {
    val Array(customerId, numberCalled, callTime) = callString.split(" ")
    Call(customerId, numberCalled, parseDuration(callTime))
  }

  def parseDuration(timeString: String): Int = {
    val time = timeString.split(":")
    time(0).toInt * 3600 + time(1).toInt * 60 + time(2).toInt
  }
}
