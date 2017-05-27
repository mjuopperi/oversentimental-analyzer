package analytics.nlp

import org.scalatest.{Matchers, WordSpec}
import overwatch.Hero._
import util.Comments._

class SentimentAnalysisSpec extends WordSpec with Matchers {

  "SentimentAnalysis" should {

    "work for simple positive statement" in {
      val sentence = toSentence("Lúcio is great!", LÚCIO)
      SentimentAnalysis.analyze(sentence)
    }

    "work for simple negative statement" in {
      val sentence = toSentence("Lúcio is horrible!", LÚCIO)
      SentimentAnalysis.analyze(sentence)
    }

    "work for simple negated positive statement" in {
      val sentence = toSentence("Lúcio is not great.", LÚCIO)
      SentimentAnalysis.analyze(sentence)
    }

    "work for simple contracted negated positive statement" in {
      val sentence = toSentence("Lúcio is'nt great.", LÚCIO)
      SentimentAnalysis.analyze(sentence)
    }

    "work for slangy contracted negated positive statement" in {
      val sentence = toSentence("Lúcio ain'nt great.", LÚCIO)
      SentimentAnalysis.analyze(sentence)
    }

    "work" ignore {
      val total = Runtime.getRuntime.totalMemory()
      val max = Runtime.getRuntime.maxMemory()
      val free = Runtime.getRuntime.freeMemory()
      //SentimentAnalysis.analyze(toSentence("Ana is not bad.", ANA))
      //SentimentAnalysis.analyze(toSentence("Ana isn't bad.", ANA))
      ////SentimentAnalysis.analyze(toSentence("Ana isn't good.", ANA))
      //SentimentAnalysis.analyze(toSentence("Torbjörn isn't good.", TORBJÖRN))
      //SentimentAnalysis.analyze(toSentence("Torbjörn is good.", TORBJÖRN))
      //SentimentAnalysis.analyze(toSentence("Symmetra was never that impressive.", SYMMETRA))
      //SentimentAnalysis.analyze(toSentence("Winston is better than Junkrat.", WINSTON))
      //SentimentAnalysis.analyze(toSentence("Winston is better than Junkrat, but not very pretty.", WINSTON))

      println(total + " -> " + Runtime.getRuntime.totalMemory())
      println(max + " -> " + Runtime.getRuntime.maxMemory())
      println(free + " -> " + Runtime.getRuntime.freeMemory())
    }
  }

}
