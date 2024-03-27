package com.baben.apps.appformation3.core.app

import android.content.SharedPreferences
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.any
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthLocalStorageTest {

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var authLocalStorage: AuthLocalStorage

    @Mock
    lateinit var mockContext: Context
    @Mock
    lateinit var mockSharedPreferences: SharedPreferences

    private val SHARED_PREFERENCE_KEY = "auth_prefs"
    @Before
    fun setUp() {
        `when`(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences)
        authLocalStorage = AuthLocalStorage(mockContext)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun test_shredPreference_initialised(){
        val argument= ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(mockContext).getSharedPreferences(argument.capture(), anyInt())
        assertNotNull(mockSharedPreferences)
        assertEquals(SHARED_PREFERENCE_KEY,argument.value)

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
