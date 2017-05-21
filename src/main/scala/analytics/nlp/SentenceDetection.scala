package analytics.nlp

import opennlp.tools.sentdetect.{SentenceDetectorME, SentenceModel}
import overwatch.HeroAliases.heroAliases
import reddit.Comment

object SentenceDetection {
  private lazy val sentenceModel = new SentenceModel(getClass.getResource("/nlp/en-sent.bin"))
  private lazy val sentenceDetector = new SentenceDetectorME(sentenceModel)

  private def detectSentences(comment: Comment) = sentenceDetector.sentDetect(comment.body)

  private def heroRegex(hero: String) = ("(^|\\W)" + hero + "($|\\W)").r
  private lazy val heroAliasRegexes = heroAliases.map {
    case (hero, aliases) => (hero, aliases.map(heroRegex))
  }

  private def classify(sentence: String, comment: Comment) = {
    val heroes = heroAliasRegexes.filter { case (_, aliases) =>
      aliases.exists(r => r.findFirstIn(sentence.toLowerCase).isDefined)
    }.keySet
    heroes.map(hero => ClassifiedSentence(comment.id, sentence, comment.created, hero)).toList
  }

  def sentencesWithHero(comment: Comment) = {
    detectSentences(comment).flatMap(sentence => classify(sentence, comment))
  }
}
