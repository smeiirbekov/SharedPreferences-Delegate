package com.sm.spe

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.typeOf

inline fun<reified V> SharedPreferences.property(
    key: String,
    default: V
) = prefs(this, key, default)

inline fun<reified V> prefs(
    prefs: SharedPreferences,
    key: String,
    default: V
) = object : ReadWriteProperty<Any, V> {

    override fun getValue(thisRef: Any, property: KProperty<*>): V = prefs[key, default]

    override fun setValue(thisRef: Any, property: KProperty<*>, value: V) = prefs.set(key, value)

}

inline operator fun<reified V> SharedPreferences.get(key: String): V {
    return when(V::class) {
        String::class -> getString(key, null) as V
        Int::class -> getInt(key, 0) as V
        Long::class -> getLong(key, 0) as V
        Boolean::class -> getBoolean(key, false) as V
        Float::class -> getFloat(key, 0f) as V
        Set::class -> {
            val currentType = typeOf<V>()
            if (currentType == typeOf<Set<String>>() || currentType == typeOf<Set<String>?>()) {
                getStringSet(key, null) as V
            } else {
                throw UnsupportedOperationException("Type ${V::class} not supported")
            }
        }
        else -> throw UnsupportedOperationException("Type ${V::class} not supported")
    }
}

inline operator fun<reified V> SharedPreferences.get(key: String, default: V): V {
    return when(V::class) {
        String::class -> getString(key, default as? String) as V
        Int::class -> getInt(key, default as? Int ?: 0) as V
        Long::class -> getLong(key, default as? Long ?: 0) as V
        Boolean::class -> getBoolean(key, default as? Boolean ?: false) as V
        Float::class -> getFloat(key, default as? Float ?: 0f) as V
        Set::class -> {
            val currentType = typeOf<V>()
            if (currentType == typeOf<Set<String>>() || currentType == typeOf<Set<String>?>()) {
                @Suppress("UNCHECKED_CAST")
                getStringSet(key, default as? Set<String>) as V
            } else {
                throw UnsupportedOperationException("Type ${V::class} not supported")
            }
        }
        else -> throw UnsupportedOperationException("Type ${V::class} not supported")
    }
}

inline operator fun<reified V> SharedPreferences.set(key: String, value: V) {
    val editor = edit()
    when(V::class) {
        String::class -> editor.putString(key, value as? String)
        Int::class -> editor.putInt(key, value as? Int ?: 0)
        Long::class -> editor.putLong(key, value as? Long ?: 0)
        Boolean::class -> editor.putBoolean(key, value as? Boolean ?: false)
        Float::class -> editor.putFloat(key, value as? Float ?: 0f)
        else -> {
            val currentType = typeOf<V>()
            if (currentType == typeOf<Set<String>>() || currentType == typeOf<Set<String>?>()) {
                @Suppress("UNCHECKED_CAST")
                editor.putStringSet(key, value as? Set<String>)
            } else {
                throw UnsupportedOperationException("Type ${V::class} not supported")
            }
        }
    }
    editor.apply()
}