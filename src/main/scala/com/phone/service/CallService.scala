package com.phone.service

import com.phone.repository.CallRepository

case class CallService(callRepository: CallRepository) {

  def amountForCustomer(
      promotionNumber: String = ""
  ): List[(String, BigDecimal)] = {
    val customers = callRepository.customers
    customers
      .map(customer =>
        (
          customer,
          callRepository
            .callsForCustomer(customer)
            .filter(_.numberCalled != promotionNumber)
            .map(_.cost)
            .reduceOption(_ + _)
            .getOrElse(BigDecimal(0.00))
        )
      )
  }

  def callDurationsByNumberCalled: Map[String, Int] = {
    callRepository.numbersCalled
      .map(number => (number, callRepository.totalDurationForNumber(number)))
      .toMap
  }
}
