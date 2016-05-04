/**
 * Created by gero.seifert on 04.05.2016.
 */
package de.heikoseeberger.scalatrain

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{ Matchers, WordSpec }
import org.scalacheck.Gen

class TimeSpec extends WordSpec with Matchers
    with GeneratorDrivenPropertyChecks {

  "Creating a Time" should {
    "throw an IllegalArgumentException for hours less than 0 of greater equal 24" in {
      forAll("hours") { (hours: Int) =>
        whenever(hours < 0 || hours >= 24) {
          an[IllegalArgumentException] should be thrownBy Time(hours)
        }
      }
    }

    "throw an IllegalArgumentException for minutes less than 0 or greater equal 60" in {
      forAll("minutes") { (minutes: Int) =>
        whenever(minutes < 0 || minutes >= 60) {
          an[IllegalArgumentException] should be thrownBy Time(minutes = minutes)
        }
      }
    }
  }

  "Calling fromMinutes" should {
    "return Time(1, 10) for 70 minutes" in {
      Time.fromMinutes(70) shouldBe Time(1, 10)
    }

    "return a correct Time for minutes within [0, 1440]" in {
      forAll(Gen.choose(0, 1440 - 1) -> "minutes") { minutes =>
        val time = Time.fromMinutes(minutes)
        time.hours shouldBe minutes / 60
        time.minutes shouldBe minutes % 60
      }
    }
  }

  "Calling minus or -" should {
    "return 0 for equal Times" in {
      Time(1, 10) - Time(1, 10) shouldBe 0
    }

    def timeGen = for {
      hours <- Gen.choose(0, 23)
      minutes <- Gen.choose(0, 59)
    } yield Time(hours, minutes)

    "return a correct value for any two Times" in {
      forAll(timeGen -> "time1", timeGen -> "time2") { (time1, time2) =>
        def asMinutes(time: Time) = time.hours * 60 + time.minutes
        time1 - time2 shouldBe asMinutes(time1) - asMinutes(time2)
      }
    }
  }
}
