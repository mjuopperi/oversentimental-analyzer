package util

import java.time.Instant

import analytics.nlp.ClassifiedSentence
import overwatch.Hero.Hero
import reddit.Comment


object Comments {
  def toComment(body: String) = Comment("t1_cmntid1", body, Instant.now.getEpochSecond)
  def toSentence(hero: Hero) =
    ClassifiedSentence("t1_cmntid1", s"Let's talk about $hero.", Instant.now.getEpochSecond, hero)
  def toSentence(body: String, hero: Hero) =
    ClassifiedSentence("t1_cmntid1", body, Instant.now.getEpochSecond, hero)
}
