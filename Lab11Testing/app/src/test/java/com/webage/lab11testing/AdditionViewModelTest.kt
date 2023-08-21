package com.webage.lab11testing

import androidx.lifecycle.ViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AdditionViewModelTest {

    lateinit var additionViewModel : AdditionViewModel

    @Before
    fun setup() {
        additionViewModel = AdditionViewModel()
    }

    @Test
    fun additionIsCorrect() {
        assertEquals(4, additionViewModel.add(2,2))
        assertEquals( 3, additionViewModel.add(10, -7))
    }
}