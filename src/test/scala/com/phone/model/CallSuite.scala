package com.phone.model

import org.scalatest.funsuite.AnyFunSuite

class CallSuite extends AnyFunSuite {

  val customerId = "A"
  val numberCalled = "111-111-111"
  val durationInSeconds = 30
  val callStringLine = "A 111-111-111 00:01:00"

  test("Call created correctly with all parameters") {
    val call = Call(customerId, numberCalled, durationInSeconds)
    assert(call.customerId.equals(customerId))
    assert(call.numberCalled.equals(numberCalled))
    assert(call.durationInSeconds.equals(durationInSeconds))
  }

  test("Call created correctly with all parameters from a String") {
    val call = Call(callStringLine)
    assert(call.customerId.equals("A"))
    assert(call.numberCalled.equals("111-111-111"))
    assert(call.durationInSeconds.equals(60))
  }

  test("Call with 0 time duration costs 0 GBP") {
    val call = Call("", "", 0)
    assert(call.cost.equals(0.0))
  }

  test("Call in second time range has exceeded rate applied") {
    val callStringLine = "A 111-111-111 00:04:00"
    val call = Call(callStringLine)
    assert(call.cost.equals(10.8))
  }

  test("Call in first time range has lower rate applied") {
    val call = Call(callStringLine)
    assert(call.cost.equals(3.0))
  }
}
