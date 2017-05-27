package analytics.nlp

import analytics.nlp.Parser.getClass
import opennlp.tools.tokenize.{TokenizerME, TokenizerModel}


object Tokenizer {
  private lazy val tokenizerModel = new TokenizerModel(getClass.getResource("/nlp/en-token.bin"))
  private lazy val tokenizer = new TokenizerME(tokenizerModel)

  def tokenizePos(text: String) = tokenizer.tokenizePos(text)
}
