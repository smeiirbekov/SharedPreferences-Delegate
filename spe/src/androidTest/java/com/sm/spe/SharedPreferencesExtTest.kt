package com.sm.spe

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class SharedPreferencesExtTest {

    private lateinit var sharedPreferences: SharedPreferences

    private val stringKey = "stringKey"
    private val stringValue = "stringValue"
    private val stringSetKey = "stringSetKey"
    private val stringSetValue = setOf("str1", "str2", "str3")
    private val intKey = "intKey"
    private val intValue = 1
    private val longKey = "longKey"
    private val longValue = 2L
    private val floatKey = "floatKey"
    private val floatValue = 3F
    private val booleanKey = "booleanKey"
    private val booleanValue = true

    private val nullStringKey = "nullStringKey"
    private val nullStringSetKey = "nullStringSetKey"
    private val nullIntKey = "nullIntKey"
    private val nullLongKey = "nullLongKey"
    private val nullFloatKey = "nullFloatKey"
    private val nullBooleanKey = "nullBooleanKey"

    private val changedStringValue = "changedStringValue"
    private val changedStringSetValue = setOf("str4", "str5", "str6")
    private val changedIntValue = 11
    private val changedLongValue = 22L
    private val changedFloatValue = 33.3F
    private val changedBooleanValue = true

    @Before
    fun before(){
        sharedPreferences = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .getSharedPreferences("TestPreferences", Context.MODE_PRIVATE)
    }

    @Test
    fun testNotNulls() {
        val editor = sharedPreferences.edit()
        editor.putString(stringKey, stringValue)
        editor.putStringSet(stringSetKey, stringSetValue)
        editor.putInt(intKey, intValue)
        editor.putLong(longKey, longValue)
        editor.putFloat(floatKey, floatValue)
        editor.putBoolean(booleanKey, booleanValue)
        editor.apply()

        assertEquals(stringValue, sharedPreferences.get<String>(stringKey))
        assertEquals(stringSetValue, sharedPreferences.get<Set<String>>(stringSetKey))
        assertEquals(intValue, sharedPreferences.get<Int>(intKey))
        assertEquals(longValue, sharedPreferences.get<Long>(longKey))
        assertEquals(floatValue, sharedPreferences.get<Float>(floatKey))
        assertEquals(booleanValue, sharedPreferences.get<Boolean>(booleanKey))
    }

    @Test
    fun testNulls() {
        sharedPreferences.edit().clear().apply()
        assertEquals(null, sharedPreferences.get<String?>(nullStringKey))
        assertEquals(null, sharedPreferences.get<Set<String>?>(nullStringSetKey))
        assertEquals(0, sharedPreferences.get<Int>(nullIntKey))
        assertEquals(0L, sharedPreferences.get<Long>(nullLongKey))
        assertEquals(0F, sharedPreferences.get<Float>(nullFloatKey))
        assertEquals(false, sharedPreferences.get<Boolean>(nullBooleanKey))
    }

    @Test
    fun testChangedValue() {
        sharedPreferences[nullStringKey] = changedStringValue
        sharedPreferences[nullStringSetKey] = changedStringSetValue
        sharedPreferences[nullIntKey] = changedIntValue
        sharedPreferences[nullLongKey] = changedLongValue
        sharedPreferences[nullFloatKey] = changedFloatValue
        sharedPreferences[nullBooleanKey] = changedBooleanValue

        assertEquals(changedStringValue, sharedPreferences.get<String?>(nullStringKey))
        assertEquals(changedStringSetValue, sharedPreferences.get<Set<String>?>(nullStringSetKey))
        assertEquals(changedIntValue, sharedPreferences.get<Int>(nullIntKey))
        assertEquals(changedLongValue, sharedPreferences.get<Long>(nullLongKey))
        assertEquals(changedFloatValue, sharedPreferences.get<Float>(nullFloatKey))
        assertEquals(changedBooleanValue, sharedPreferences.get<Boolean>(nullBooleanKey))
    }
}