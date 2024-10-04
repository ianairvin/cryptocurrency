package com.irvin.cryptocurrency.presentation.viewmodels

import com.irvin.cryptocurrency.MainDispatcherRule
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.domain.entities.InfoCryptocurrency
import com.irvin.cryptocurrency.domain.usecases.GetCryptocurrenciesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CryptocurrenciesVMTest {

    private lateinit var sut: CryptocurrenciesVM

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    val ioDispatcher = StandardTestDispatcher()

    @MockK
    lateinit var getCryptocurrenciesUseCase: GetCryptocurrenciesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery { getCryptocurrenciesUseCase(Currency.USD) } returns Result.success(createListCryptocurrencies())
        sut = CryptocurrenciesVM(getCryptocurrenciesUseCase)
    }

    @Test
    fun `updateListFromPullToRefresh WHEN result success EXPECT error false and uiState is Cryptocurrencies`()
    = runTestUnconfined {
        val expectedUiState = CryptocurrenciesUiState.Cryptocurrencies(createListCryptocurrencies())

        val error = sut.updateListFromPullToRefresh()

        assertFalse(error)
        assertEquals(expectedUiState, sut.uiState.value)
    }

    @Test
    fun `updateListFromPullToRefresh WHEN result failure EXPECT error true`()
    = runTestUnconfined {
        coEvery { getCryptocurrenciesUseCase(Currency.USD) } returns Result.failure(Exception())

        val error = sut.updateListFromPullToRefresh()

        assertTrue(error)
    }

    @Test
    fun `changeStateToLoading WHEN state is Initial EXPECT state will become Loading`() {
        val expectedState = CryptocurrenciesUiState.Loading
        val uiState = sut::class.java.getDeclaredField("_uiState")
        uiState.isAccessible = true
        uiState.set(sut, MutableStateFlow(CryptocurrenciesUiState.Initial))

        sut.changeStateToLoading()

        assertEquals(expectedState, sut.uiState.value)
    }

    @Test
    fun `changePickedCurrency WHEN picked currency is USD EXPECT picked currency will become RUB`()
    {
        val expectedPickedCurrency = Currency.RUB

        sut.changePickedCurrency()

        assertEquals(expectedPickedCurrency, sut.pickedCurrency.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `startObservingUiState WHEN state is Loading EXPECT uiState will become Cryptocurrencies`() =
        runTestStandard {
            val expectedUiState = CryptocurrenciesUiState.Cryptocurrencies(createListCryptocurrencies())
            val uiStateField = sut::class.java.getDeclaredField("_uiState")
            uiStateField.isAccessible = true
            uiStateField.set(sut, MutableStateFlow(CryptocurrenciesUiState.Initial))

            uiStateField.set(sut, MutableStateFlow(CryptocurrenciesUiState.Loading))
            advanceUntilIdle()

            assertEquals(expectedUiState, sut.uiState.value)
        }

    private fun createListCryptocurrencies(): List<Cryptocurrency> {
        return listOf(
                Cryptocurrency(
                    id = "1",
                    name = "Bitcoin",
                    shortName = "BTC",
                    price = 57000.58,
                    priceChangePercentage = 4.0,
                    image = ""
                )
            )
    }

    private fun createSuccessResultInfoCryptocurrency(
        id: String = "",
        image: String = "",
        categories: List<String> = emptyList(),
        description: String = ""
    ): Result<InfoCryptocurrency> {
        return Result.success(InfoCryptocurrency(id, image, categories, description))
    }

    private fun runTestStandard(testBody: suspend TestScope.() -> Unit) = runTest(
        context = StandardTestDispatcher(),
        testBody = testBody
    )

    private fun runTestUnconfined(testBody: suspend TestScope.() -> Unit) = runTest(
        context = UnconfinedTestDispatcher(),
        testBody = testBody
    )
}