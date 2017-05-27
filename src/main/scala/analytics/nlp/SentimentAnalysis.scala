package analytics.nlp

import java.io.File

import analytics.nlp.sentiment.SimpleDeclarativeClause
import opennlp.tools.parser.Parse

import scala.io.Source


object SentimentAnalysis {

  val adjectiveTags = List("")

  private lazy val sentimentData = {
    val files = new File(getClass.getResource("/nlp/sentiment").getPath).listFiles()
    files.map(_.toURI).flatMap(Source.fromURI(_).getLines)
  }

  private lazy val sentiments = {
    sentimentData
      .map(_.split("\t"))
      .map(pair => (pair(0), pair(1).toInt))
      .toMap
  }

  def printParse(parse: Parse, level: Int = 0): Unit = {
    println(s"${" " * (level * 2)}${parse.getCoveredText}: ${parse.getType}")
    println(s"${" " * (level * 2)}Child count: ${parse.getTagNodes.length}")
    println(s"${" " * (level * 2)}Children:    ${parse.getTagNodes.map(_.getCoveredText).mkString(", ")}")
    parse.getTagNodes.foreach(printParse(_, level + 1))
  }

  def getTags(parse: Parse) = parse.getTagNodes.map(_.getType).mkString(" ")

  def analyze(sentence: ClassifiedSentence) = {
    val parse = Parser.parse(sentence)
    val analysis = parse.getChildren.head.getType match {
      case "S" => SimpleDeclarativeClause(parse, sentence)
      case _ => ""
    }

    println(parse.show())
    println(getTags(parse))
    println(sentiments("happy"))
    println(sentiments("overpowered"))
  }

}
