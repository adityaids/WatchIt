package com.aditya.watchit.utils

import com.aditya.watchit.R
import com.aditya.watchit.data.FilmModel

object DummyData {
    fun generateMovieDummy(): List<FilmModel> {
        val listMovies = ArrayList<FilmModel>()
        listMovies.add(
            FilmModel(
                "John Wick Chapter 3 - Parabellum",
                "Movies",
                "In this third installment of the adrenaline-fueled action franchise, skilled assassin John Wick (Keanu Reeves) returns with a \$14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin's guild, the High Table, John Wick is excommunicado, but the world's most ruthless hit men and women await his every turn.",
                "R.drawable.john_wick_chapter_3_parabellum"
            )
        )
        listMovies.add(
            FilmModel(
                "Avenger - Infinity War",
                "Movies",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment, the fate of Earth and existence has never been more uncertain.",
                "R.drawable.avenger_infinity_war"
            )
        )
        listMovies.add(
            FilmModel(
                "Avenger - End Game",
                "Movies",
                "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos's actions and undo the chaos to the universe, no matter what consequences may be in store, and no matter who they face",
                "R.drawable.avenger_end_game"
            )
        )
        listMovies.add(
            FilmModel(
                "Captain Marvel",
                "Movies",
                "After crashing an experimental aircraft, Air Force pilot Carol Danvers is discovered by the Kree and trained as a member of the elite Starforce Military under the command of her mentor Yon-Rogg. Six years later, after escaping to Earth while under attack by the Skrulls, Danvers begins to discover there's more to her past. With help from S.H.I.E.L.D. agent Nick Fury, they set out to unravel the truth.",
                "R.drawable.captain_marvel"
            )
        )
        listMovies.add(
            FilmModel(
                "Guardians of the Galaxy Vol. 2",
                "Movies",
                "After saving Xandar from Ronan's wrath, the Guardians are now recognized as heroes. Now the team must help their leader Star Lord (Chris Pratt) uncover the truth behind his true heritage. Along the way, old foes turn to allies and betrayal is blooming. And the Guardians find that they are up against a devastating new menace who is out to rule the galaxy.",
                "R.drawable.guardian_of_the_galaxy_vol_2"
            )
        )
        listMovies.add(
            FilmModel(
                "Spiderman - Homecoming",
                "Movies",
                "A young Peter Parker/Spider-Man begins to navigate his newfound identity as the web-slinging super hero Spider-Man. Thrilled by his experience with the Avengers, Peter returns home, where he lives with his Aunt May, under the watchful eye of his new mentor Tony Stark. Peter tries to fall back into his normal daily routine - distracted by thoughts of proving himself to be more than just your friendly neighborhood Spider-Man - but when the Vulture emerges as a new villain, everything that Peter holds most important will be threatened.",
                "R.drawable.spiderman_home_coming"
            )
        )
        listMovies.add(
            FilmModel(
                "Star Wars: Episode VIII - The Last Jedi",
                "Movies",
                "Jedi Master-in-hiding Luke Skywalker unwillingly attempts to guide young hopeful Rey in the ways of the force, while Leia, former princess turned general, attempts to lead what is left of the Resistance away from the ruthless tyrannical grip of the First Order.",
                "R.drawable.star_wars_episode_viii_the_last_jedi"
            )
        )
        listMovies.add(
            FilmModel(
                "Terminator - Dark Fate",
                "Movies",
                "A young female Mexican worker, Dani Ramos, is hunted down by a virtually indestructible terminator from the future called a REV-9. However, she is protected by an enhanced human named Grace who is also from the future. They flee from the unstoppable terminator and, out of the blue, Sarah Connor helps them on the road. All three head to Laredo, Texas, where Grace has the coordinates of a possible support and where they meet a T-800 who is living in an isolated location with his family. The group teams up to try to destroy the REV-9.",
                "R.drawable.terminator_dark_fate"
            )
        )
        listMovies.add(
            FilmModel(
                "The Hunger Games - Mockingjay part 1",
                "Movies",
                "With the Games destroyed, Katniss Everdeen, along with Gale, Finnick and Beetee, end up in the so thought \"destroyed\" District 13. Under the leadership of President Coin and the advice of her friends, Katniss becomes the \"Mockingjay\", the symbol of rebellion for the districts of Panem.",
                "R.drawable.the_hunger_games"
            )
        )
        listMovies.add(
            FilmModel(
                "Thor - Ragnarok",
                "Movies",
                "Imprisoned on the other side of the universe, the mighty Thor (Chris Hemsworth) finds himself in a deadly gladiatorial contest that pits him against The Incredible Hulk (Mark Ruffalo), his former ally and fellow Avenger. Thor's quest for survival leads him in a race against time to prevent the all-powerful Hela (Cate Blanchett) from destroying his home world and the Asgardian civilization.",
                "R.drawable.thor_ragnarok"
            )
        )
        return listMovies
    }
    fun generateTvDummy(): List<FilmModel>{
        val listTv = ArrayList<FilmModel>()
        listTv.add(
            FilmModel(
                "Black Mirror",
                "Tv Series",
                "Set in a world only minutes from our own, \"Black Mirror\" unveils how modern technologies can backfire and be used against their makers, every episode set in a slightly different reality with different characters combating different types of technologies.",
                "R.drawable.tv_black_mirror"
            )
        )
        listTv.add(
            FilmModel(
                "Bojack Horseman",
                "Tv Series",
                "After starring in the popular sitcom \"Horsin' Around\" in the late 80s and early 90s, BoJack Horseman struggles with his deteriorating popularity, depression, addiction, and maintaining the relationships with those he cares about, but can't seem to stop hurting, all while living in the satirical, pun-filled city of Hollywood, California.",
                "R.drawable.tv_bojack_horseman"
            )
        )
        listTv.add(
            FilmModel(
                "Breaking Bad",
                "Tv Series",
                "When chemistry teacher Walter White is diagnosed with Stage III cancer and given only two years to live, he decides he has nothing to lose. He lives with his teenage son, who has cerebral palsy, and his wife, in New Mexico. Determined to ensure that his family will have a secure future, Walt embarks on a career of drugs and crime. He proves to be remarkably proficient in this new world as he begins manufacturing and selling methamphetamine with one of his former students. The series tracks the impacts of a fatal diagnosis on a regular, hard working man, and explores how a fatal diagnosis affects his morality and transforms him into a major player of the drug trade.",
                "R.drawable.tv_breaking_bad"
            )
        )
        listTv.add(
            FilmModel(
                "Dear White People",
                "Tv Series",
                "At a predominantly white Ivy League college, a group of black students navigate various forms of racial and other types of discrimination.",
                "R.drawable.tv_dear_white_people"
            )
        )
        listTv.add(
            FilmModel(
                "Explained",
                "Tv Series",
                "A documentary series that looks to explore the big questions of today.",
                "R.drawable.tv_explained"
            )
        )
        listTv.add(
            FilmModel(
                "Orange is The New Black",
                "Tv Series",
                "Piper Chapman is a public relations executive with a career and a fiance when her past suddenly catches up to her. In her mid-30s she is sentenced to spend time in a minimum-security women's prison in New York for her association with a drug runner 10 years earlier. This Netflix original series is based on the book of the same title. Forced to trade power suits for prison orange, Chapman makes her way through the corrections system and adjusts to life behind bars, making friends with the many eccentric, unusual and unexpected people she meets.",
                "R.drawable.tv_orange_is_the_new_black"
            )
        )
        listTv.add(
            FilmModel(
                "Queer Eye",
                "Tv Series",
                "More than a decade after the original series went off the air, Netflix reboots the \"Queer Eye\" franchise with a new Fab Five and a new setting, trading in the concrete jungle of New York City for communities in and around Atlanta. The style experts forge relationships with men and women who often have different beliefs from them, leading to moments of social commentary interspersed with style advice. Advising people in need of lifestyle makeovers are food and wine specialist Antoni Porowski, interior designer Bobby Berk, grooming consultant Jonathan Van Ness, fashion designer Tan France and culture expert Karamo Brown, who reality TV fans may recognize as one of the housemates on \"The Real World (1992): Philadelphia.\" David Collins, who created the original Queer Eye (2003) show, is on board as an executive producer.",
                "R.drawable.tv_queer_eye"
            )
        )
        listTv.add(
            FilmModel(
                "Russian Doll",
                "Tv Series",
                "On Nadia's 36th birthday she is struck by a car and killed while leaving her party. In an instant she is alive again and transported back to her birthday party earlier that night. Moments later she dies again and finds herself, once again, back at her party, Nadia begins to question her sanity as she strives to unravel the mystery of her situation.",
                "R.drawable.tv_russian_doll"
            )
        )
        listTv.add(
            FilmModel(
                "The Chef Show",
                "Tv Series",
                "Writer, director and food enthusiast Jon Favreau and chef Roy Choi explore food in and out of the kitchen with accomplished chefs and celebrity friends.",
                "R.drawable.tv_the_chef_show"
            )
        )
        listTv.add(
            FilmModel(
                "The Good Place",
                "Tv Series",
                "The Good Place is a town where those who have been good throughout their lives go once they have passed away. Michael (Danson) is the architect who oversees the town--and this is the first one he has been in charge of creating. Eleanor (Bell) arrives at the Good Place and realizes they have her name right, but everything else is wrong. She isn't meant to be there at all. With the help of Chidi, her soul mate (Harper), Eleanor tries to right her wrongs, seeking to finally earn her spot in the Good Place.",
                "R.drawable.tv_the_good_place"
            )
        )
        return listTv
    }
}