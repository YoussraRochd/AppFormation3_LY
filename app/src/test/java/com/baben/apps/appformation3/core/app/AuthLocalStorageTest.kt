package com.baben.apps.appformation3.core.app
import android.content.SharedPreferences
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AuthLocalStorageTest {

    @Mock

    private lateinit var editor: SharedPreferences.Editor

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var authLocalStorage: AuthLocalStorage

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        authLocalStorage = AuthLocalStorage(sharedPreferences)

    }

    @After
    fun tearDown() {
    }


    @Test
    fun saveTokenSuccess()
    {
        val token = "auzNN7V0aB30poSilNi15HCi"
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(authLocalStorage.authTokenKey, token)).thenReturn(editor)
        val resultToken= authLocalStorage.saveToken(token)
        assertEquals(true,resultToken)
    }
    @Test
    fun saveToken_failed() {
        val token = "auzNN7V0aB30poSilNi15HCi"
        val invalidToken = "invalid_token"
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(authLocalStorage.authTokenKey, token)).thenReturn(editor)
        val resultToken=authLocalStorage.saveToken(token)
        assertNotEquals(invalidToken, resultToken)
    }

    @Test
    fun getTokenSuccess() {
        val expectedToken = "auzNN7V0aB30poSilNi15HCi"
        `when`(sharedPreferences.getString(authLocalStorage.authTokenKey, null)).thenReturn(expectedToken)
        val actualToken = authLocalStorage.getToken()
        assertEquals(expectedToken, actualToken)
    }

    @Test
    fun UserConnected()
    {
        val token="auzNN7V0aB30poSilNi15HCi"
        `when`(sharedPreferences.getString(authLocalStorage.authTokenKey, null)).thenReturn(token)
        val result = authLocalStorage.isLogged()
        assertEquals(true, result)

    }

    @Test
    fun UserNotConnected()
    {
        val token=null
        authLocalStorage.saveToken(token)
        `when`(sharedPreferences.getString(authLocalStorage.authTokenKey, null)).thenReturn(token)
        val result = authLocalStorage.isLogged()
        assertEquals(false, result)

    }
}