package com.phone.parser

import org.scalatest.funsuite.AnyFunSuite

class FileParserSuite extends AnyFunSuite {

  val testFilePath = "./src/test/resources/calls_test_file.log"

  test("ParseFile correctly parses a file") {
    val parsedFile = FileParser.parseFile(testFilePath)
    assert(parsedFile.head.equals("C 111-111-111 00:01:15"))
    assert(parsedFile(1).equals("C 222-222-222 00:08:38"))
  }

  test("ParseCalls correctly converts a list of records to a list of calls") {
    val parsedFile = FileParser.parseFile(testFilePath)
    val calls = FileParser.parseCalls(parsedFile)
    assert(calls.head.durationInSeconds.equals(75))
    assert(calls(1).durationInSeconds.equals(518))
  }
}
