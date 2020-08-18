package com.phone.repository

import com.phone.model.Call

case class CallRepository(calls: List[Call] = List.empty) {

  val numbersCalled: List[String] = {
    calls.map(_.numberCalled).distinct
  }

  val customers: List[String] = calls.map(_.customerId).distinct

  def totalDurationForNumber(numberCalled: String): Int = {
    calls
      .filter(_.numberCalled.equals(numberCalled))
      .map(_.durationInSeconds)
      .sum
  }

  def callsForCustomer(customerId: String): List[Call] = {
    calls.filter(_.customerId.equals(customerId))
  }
}
