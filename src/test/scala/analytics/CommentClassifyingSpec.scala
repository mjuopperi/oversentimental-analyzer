package analytics

import java.time.Instant

import org.scalatest.{Matchers, WordSpec}
import overwatch.HeroAliases._
import overwatch.Hero
import overwatch.Hero._
import reddit.Comment

import scala.util.Random


class CommentClassifyingSpec extends WordSpec with Matchers {

  def toComment(body: String) = Comment("t1_cmntid1", body, Instant.now.getEpochSecond)

  "Comment classifier" should {
    "find all heroes mentioned in comment body" in {
      val heroes = Random.shuffle(Hero.values.toList)
      val comment = toComment(heroes.mkString(" and "))
      ClassifiedComment.classify(comment).heroes should contain allElementsOf heroes
    }

    "find heroes by aliases" in {
      val heroesAndComments = for {
        hero <- Hero.values.toList
        aliases = heroAliases(hero).drop(1) // First one is same as the enum value so we drop it
        comments = aliases.map(alias => toComment(s"Let's talk about $alias."))
        if aliases.nonEmpty
      } yield(hero, comments)

      heroesAndComments.foreach {
        case (hero, comments) => comments.foreach { comment =>
          ClassifiedComment.classify(comment).heroes should be(Set(hero))
        }
      }
    }

    "ignore hero names inside words" in {
      val comment = toComment("Analytics should not pick up unrelated hero. Just Lucio.")
      ClassifiedComment.classify(comment).heroes should not contain ANA
      ClassifiedComment.classify(comment).heroes should contain(LÚCIO)
    }
  }
}
