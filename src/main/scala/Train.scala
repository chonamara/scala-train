/**
 * Created by gero.seifert on 29.04.2016.
 */
package de.heikoseeberger.scalatrain

case class Train(kind: String, number: Int) {
  require(kind.nonEmpty, "kind must not be empty!")
}
