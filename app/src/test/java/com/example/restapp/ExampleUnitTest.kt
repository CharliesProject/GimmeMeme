package com.example.restapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.restapp.overview.MyApiStatus
import com.example.restapp.overview.OverviewViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


class ExampleUnitTest {

    private lateinit var viewModel: OverviewViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = OverviewViewModel()
    }

    @Test
    fun start_state() {
        val state = MyApiStatus.START
        viewModel.apply {
            status.observeForever {}
            startPhoto()
        }
        assertEquals(state, viewModel.status.value)
    }

    @Test
    fun loading_state() {
        val state = MyApiStatus.LOADING
        viewModel.apply {
            status.observeForever {}
            setStatusLoading()
        }
        assertEquals(state, viewModel.status.value)
    }

    @Test
    fun delete_photo() {
        val photo = viewModel.emptyPhoto
        viewModel.apply {
            jsonObj.observeForever {}
            deletePhoto()

            assertEquals(photo, viewModel.jsonObj.value)
        }
    }

    @Test
    fun empty_photo() {
        val photo = viewModel.emptyPhoto
        viewModel.apply {
            jsonObj.observeForever {}
            getMyPhotos()
        }
        assertEquals(photo, viewModel.jsonObj.value)
    }

}