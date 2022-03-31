package com.example

import com.example.domain.formatAboutText
import org.junit.Assert
import org.junit.Test

class ApplicationTest {

    private val testAboutText = """
        Age: 15–16
        Birthdate: July 28, 1967
        Height: 161 cm (5'3")
        Hobbies: collecting cute things, obsessing over cute things, treasure hunting
        Famous quote: "Friends. Those companions you speak of are only friends during those fun, yet unimportant times. When painful times come, they won't be your ally."

        Rena is the main female protagonist of the question arcs and a girl who returned from Ibaraki to Hinamizawa one year ago, she is in the same grade level as Keiichi.

        She is very kind and takes care of Keiichi whenever possible, but is also naïve and is usually subject to light teasing by Keiichi, it is hinted later in the series that she loves him. She is distinguished by her obsession with things she perceives as adorable calling them kaaii (かぁいい, a slurred form of kawaii (かわいい), meaning "cute"), which are usually moekko characters or items she finds while scouring the local dump. Generally, these things are not considered cute by others.

        She also utters the phrase hau (はぅ) when excited or flustered, and has a habit of repeating phrases at the ends of her sentences, most famously "kana? kana??" (かな? かな??, lit. "I wonder, I wonder?").

        Every now and then, she goes treasure hunting in the town's trash heap, searching for 'cute' things to collect, and whenever she sees something that grabs her attention, she proclaims, "I wanna take it home!" (お持ち帰り, Omochikaerī) and proceeds to try and do so, becoming virtually unstoppable during these intervals.

        When in "Take it Home" mode, she comically becomes stronger and faster. Despite this disarming trait, Rena is shown to be amazingly observant and perceptive about the things around her (in Watanagashi-hen, she was able to deduce that both Rika and Satoko went to the Sonozaki house by looking through the contents of their fridge and a flier).

        According to Mion, while Rena might seem cute herself, people should be careful not to anger her as she becomes quite scary when angered.
    """.trimIndent()

    @Test
    fun formatAboutTest() {
        val expected = """
        Age: 15–16
        Birthdate: July 28, 1967
        Height: 161 cm (5'3")
        Hobbies: collecting cute things, obsessing over cute things, treasure hunting
        Famous quote: "Friends. Those companions you speak of are only friends during those fun, yet unimportant times. When painful times come, they won't be your ally."
        """.trimIndent()

        val formattedAbout = formatAboutText(testAboutText)

        Assert.assertEquals(expected, formattedAbout)
    }
}