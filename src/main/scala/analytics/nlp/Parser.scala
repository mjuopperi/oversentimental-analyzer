package analytics.nlp

import opennlp.tools.parser.{AbstractBottomUpParser, Parse, ParserFactory, ParserModel}
import opennlp.tools.util.Span


object Parser {
  // TODO: Figure out where to store this since it's huge
  private lazy val parserModel = new ParserModel(getClass.getResource("/nlp/en-parser-chunking.bin"))
  private lazy val parser = ParserFactory.create(parserModel)

  private def parseSentence(text: String) = {
    val parse = new Parse(text, new Span(0, text.length), AbstractBottomUpParser.INC_NODE, 1, 0)
    val spans = Tokenizer.tokenizePos(text)
    spans.zipWithIndex.foreach {
      case (span, i) => parse.insert(new Parse(text, span, AbstractBottomUpParser.TOK_NODE, 0, i))
    }
    parse
  }

  def parse(sentence: ClassifiedSentence) = {
    val text = sentence.sentence.trim
    val parsed = parser.parse(parseSentence(text))
    // Parent node is TOP. Since we only have full sentences, we can just return the next level
    parsed.getChildren.head
  }
}
