package com.phone.service

import org.scalatest.funsuite.AnyFunSuite
import com.phone.model.Call
import com.phone.repository.CallRepository

class CallServiceSuite extends AnyFunSuite {
  val calls = List(
    Call("D", "1", 10),
    Call("D", "1", 20),
    Call("E", "1", 30),
    Call("D", "2", 500)
  )
  val callService: CallService = CallService(CallRepository(calls))

  test("totalAmountSpentPerCustomer returns correct result") {
    val totalSpentPerCustomer = callService.amountForCustomer()
    assert(totalSpentPerCustomer.head.equals("D", 20.1))
    assert(totalSpentPerCustomer(1).equals("E", 1.5))
  }

  test("totalAmountSpentPerCustomer applies promotion") {
    val promoNumber = "1"
    val totalSpentPerCustomer = callService.amountForCustomer(promoNumber)
    assert(totalSpentPerCustomer.head.equals("D", 18.6))
    assert(totalSpentPerCustomer(1).equals("E", 0.0))
  }

  test("callDurationsByPhoneNumber returns correct result") {
    assert(callService.callDurationsByNumberCalled.size.equals(2))
    assert(callService.callDurationsByNumberCalled("1").equals(60))
    assert(callService.callDurationsByNumberCalled("2").equals(500))
  }

}
