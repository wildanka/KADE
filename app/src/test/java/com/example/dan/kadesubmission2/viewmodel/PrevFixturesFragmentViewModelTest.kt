package com.example.dan.kadesubmission2.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.dan.kadesubmission2.model.entity.TeamLogo
import com.example.dan.kadesubmission2.repository.FixtureRepository
import com.nhaarman.mockito_kotlin.atLeastOnce
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.junit.Rule


class PrevFixturesFragmentViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fixtureRepository: FixtureRepository

    @Mock
    private lateinit var teamLogo: TeamLogo

    @Mock
    private lateinit var detailsViewModel: DetailsActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadTeamLogo() { //TODO : melakukan test ketika melakukan request logo club
        val id = "133604"
        val fixtureRepository = mock(FixtureRepository::class.java)

        //mock teamLogo
        teamLogo.idTeam = "133604"
        teamLogo.linkClubLogo = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png"
        val logoFeed = MutableLiveData<String>() //expected output
        logoFeed.postValue(	"https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png")
        `when`(fixtureRepository.fetchTeamLogo(id)).thenReturn(logoFeed)
        fixtureRepository.fetchTeamLogo(id)

        assertEquals(logoFeed.value,fixtureRepository.fetchTeamLogo(id).value)
    }

    @Test
    fun testGetHomeClubLogoFeed() { //TODO : melakukan test ketika melakukan request logo club
        val id = "133604"
        detailsViewModel.getHomeClubLogoFeed(id)
        verify(detailsViewModel, atLeastOnce()).getHomeClubLogoFeed(id)
    }

}