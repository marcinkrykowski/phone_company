package com.phone.repository

import org.scalatest.funsuite.AnyFunSuite
import com.phone.model.Call
import com.phone.parser.FileParser

class CallRepositorySuite extends AnyFunSuite {

  val testFilePath = "./src/test/resources/calls_test_file.log"
  val parsedFile: List[String] = FileParser.parseFile(testFilePath)

  val calls = List(
    Call("D", "1", 10),
    Call("D", "1", 20),
    Call("E", "1", 30),
    Call("D", "2", 500)
  )

  test("CallRepository is created correctly with default empty list") {
    val calls = List.empty
    val callRepository = CallRepository()
    assert(callRepository.calls.equals(calls))
  }

  test("totalDurationForNumber returns correct result") {
    val callRepository = CallRepository(calls)
    assert(callRepository.totalDurationForNumber("1").equals(60))
    assert(callRepository.totalDurationForNumber("2").equals(500))
  }

  test("CallRepository is created correctly with calls") {
    val calls = parsedFile.map(callString => Call(callString))
    val callRepository = CallRepository(calls)
    assert(callRepository.calls.length.equals(2))
  }

  test("numbersCalled returns correct numbers") {
    val callRepository = CallRepository(calls)
    assert(callRepository.numbersCalled.size.equals(2))
    assert(callRepository.numbersCalled.headOption.contains("1"))
    assert(callRepository.numbersCalled.lift(1).contains("2"))
  }

  test("customers returns correct customer") {
    val calls = parsedFile.map(callString => Call(callString))
    val callRepository = CallRepository(calls)
    assert(callRepository.customers.size.equals(1))
    assert(callRepository.customers.head.equals("C"))
  }

  test("callsForCustomer returns correct calls") {
    val calls = List(Call("D", "", 10), Call("D", "", 20), Call("E", "", 30))
    val callRepository = CallRepository(calls)
    assert(callRepository.callsForCustomer("D").length.equals(2))
    assert(callRepository.callsForCustomer("E").length.equals(1))
  }

}
