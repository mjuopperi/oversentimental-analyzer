package analytics.nlp

import org.scalatest.{Matchers, WordSpec}
import overwatch.Hero
import overwatch.Hero._
import overwatch.HeroAliases.heroAliases
import util.Comments._


class SentenceDetectionSpec extends WordSpec with Matchers {

  "SentenceDetection" should {
    "detect all sentences in comment body that mention a hero" in {
      val comment = toComment("""
          |This multiline comment
          |includes Hanzo. It has three
          |sentences and two heroes.
          |The second hero is Lúcio.
        """.stripMargin)
      val expected = List(
        ClassifiedSentence(comment.id, "This multiline comment\nincludes Hanzo.", comment.created, HANZO),
        ClassifiedSentence(comment.id, "The second hero is Lúcio.", comment.created, LÚCIO)
      )

      val sentences = SentenceDetection.sentencesWithHero(comment)

      sentences.length should be(2)
      sentences should contain allElementsOf expected
    }

    "detect sentences by all hero aliases" in {
      val heroesAndComments = for {
        hero <- Hero.values.toList
        aliases = heroAliases(hero)
        comments = aliases.map(alias => toComment(s"Let's talk about $alias."))
        if aliases.nonEmpty
      } yield(hero, comments)

      heroesAndComments.foreach {
        case (hero, comments) => comments.foreach { comment =>
          val classifiedSentences = SentenceDetection.sentencesWithHero(comment)
          classifiedSentences should have length 1
          classifiedSentences.head.hero should be(hero)
        }
      }
    }

    "detect multiple heroes from same sentence" in {
      val comment = toComment("Ana is my main but Mercy is cool too.")
      val classifiedSentences = SentenceDetection.sentencesWithHero(comment)

      classifiedSentences should have length 2
      classifiedSentences.map(_.hero) should contain allElementsOf List(ANA, MERCY)
    }

    "detect same hero only once" in {
      val comment = toComment("Roadhog hog Roadhog")
      val classifiedSentences = SentenceDetection.sentencesWithHero(comment)

      classifiedSentences should have length 1
      classifiedSentences.map(_.hero) should contain(ROADHOG)
    }
  }
}
