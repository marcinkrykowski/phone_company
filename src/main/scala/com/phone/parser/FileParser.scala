package com.phone.parser

import com.phone.model.Call

import scala.io.Source

object FileParser {

  def parseCallsFromFile(fileName: String): List[Call] = {
    parseCalls(parseFile(fileName))
  }

  def parseFile(fileName: String): List[String] = {
    val source = Source.fromFile(fileName)
    val lines = source.getLines.filter(_.length > 0).toList
    source.close()
    lines
  }

  def parseCalls(callStrings: List[String]): List[Call] =
    callStrings.map(Call(_))
}
