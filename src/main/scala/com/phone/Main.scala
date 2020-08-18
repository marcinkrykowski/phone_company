package com.phone

import com.phone.model.Call
import com.phone.parser.FileParser
import com.phone.repository.CallRepository
import com.phone.service.CallService

import scala.math.BigDecimal.RoundingMode

object Main extends App {
  val sourceFilePath: String = "./src/main/resources/calls.log"
  val calls: List[Call] = FileParser.parseCallsFromFile(sourceFilePath)
  val callService: CallService = CallService(CallRepository(calls))
  val callDurationsByNumber: Map[String, Int] =
    callService.callDurationsByNumberCalled
  val promotionNumber: String = callDurationsByNumber.maxBy(_._2)._1
  val totalMoneyAmountSpentPerCustomer: List[(String, BigDecimal)] =
    callService.amountForCustomer(promotionNumber)
  totalMoneyAmountSpentPerCustomer.foreach {
    case (customer, cost) =>
      println(
        "Customer " + customer + " spent " + cost
          .setScale(2, RoundingMode.CEILING) + " GBP"
      )
  }
}
