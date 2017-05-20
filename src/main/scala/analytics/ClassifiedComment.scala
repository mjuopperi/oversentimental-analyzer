package analytics

import overwatch.Hero.Hero
import overwatch.HeroAliases.heroAliases
import reddit.Comment

case class ClassifiedComment(comment: Comment, heroes: Set[Hero])

object ClassifiedComment {

  private def heroRegex(hero: String) = ("(^|\\W)" + hero + "($|\\W)").r
  private lazy val heroAliasRegexes = heroAliases.map {
    case (hero, aliases) => (hero, aliases.map(heroRegex))
  }

  def classify(comment: Comment) = {
    val lowerCaseComment = comment.body.toLowerCase
    val heroes = heroAliasRegexes.filter { case (_, aliases) =>
      aliases.exists(r => r.findFirstIn(lowerCaseComment).isDefined)
    }.keySet
    ClassifiedComment(comment, heroes)
  }
}
