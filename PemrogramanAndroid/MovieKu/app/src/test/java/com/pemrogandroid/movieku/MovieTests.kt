package com.pemrogandroid.movieku

import com.pemrogandroid.movieku.model.Movie
import org.junit.Assert
import org.junit.Test

class MovieTests {

    @Test
    fun testGetReleaseYearFromStringFromattedDate(){
        val movie = Movie(title = "Finding Nemo", releaseDate = "2003-05-30")
        Assert.assertEquals("2003", movie.getReleaseYearFromDate())
    }

    @Test
    fun testGetReleaseYearFromYear(){
        val movie = Movie(title = "Finding Nemo", releaseDate = "2003")
        Assert.assertEquals("2003", movie.getReleaseYearFromDate())
    }

    @Test
    fun testGetReleaseYearCaseEmpty(){
        val movie = Movie(title = "Finding Nemo", releaseDate = "")
        Assert.assertEquals("", movie.getReleaseYearFromDate())
    }

    @Test
    fun testGetReleaseYearReleaseDateNotSuplied(){
//        val movie = Movie(title = "Finding Nemo" )
//        Assert.assertEquals("", movie.getReleaseYearFromDate())
    }
}