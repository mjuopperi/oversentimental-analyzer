package analytics.nlp.sentiment

import analytics.nlp.ClassifiedSentence
import opennlp.tools.parser.{AbstractBottomUpParser, Parse}


case class SimpleDeclarativeClause(parse: Parse, sentence: ClassifiedSentence) {

  private def findVerbPhrase(parse: Parse): Option[Parse] = {
    parse.getType match {
      case "VP" => Some(parse)
      case AbstractBottomUpParser.TOK_NODE => None
      case _ => parse.getChildren.map(findVerbPhrase).head
    }
  }

  private def isNegated(parse: Parse) = {
    val negations = List("not", "'nt")
    (for {
      verbPhrase <- findVerbPhrase(parse)
      adverbs = verbPhrase.getChildren.filter(_.getType == "RB")
      adjectives = verbPhrase.getChildren.filter(_.getType == "JJ")
      texts = (adverbs ++ adjectives).map(_.getCoveredText)
    } yield texts.map(_.toLowerCase).filter(negations.contains)).getOrElse(false) // count must be odd
  }

  private def findAdjectives(parse: Parse) = {

  }

  def sentiment() = {

    //if (isNegated(parse))
    ""
  }
}
