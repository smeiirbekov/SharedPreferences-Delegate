# SharedPreferences Property Delegate

Easy SharedPreferences with Kotlin Property Delegate to use with default values. Useful with wrapper classes like PrefManager to define with const Keys

Usage
-----
```kotlin
// Your SharedPreferences, can be any preference implementations
private val sharedPreferences = context.getSharedPreferences("YourSharedPrefsName", Context.MODE_PRIVATE)

// Alternatively: private var name: String by sharedPreferences.property("KEY_NAME", "default_value")
private var name: String by prefs(sharedPreferences, "KEY_NAME", "default_value")
// Note: For types Int, Long, Float, Boolean you will always get zero value, whether you set null or zero

fun delegateExample() {
  println(name) // Prints: "default_value"
  name = "changed name"
  println(name) // Prints: "changed name"
}

fun accessOperatorExample(){
  // Note: Kotlin should be able to infer types, otherwise you might run into unexpected behavior
  sharedPreferences["KEY_NAME"] = "changed by setter"
  val myName: String = sharedPreferences["KEY_NAME"] // Provide infer type: String
  println(myName) // Prints: "changed by setter"
  
  val unsetValue: String? = sharedPreferences["KEY_NONEXISTENT_VALUE"] // Provide infer type: String? (nullable String)
  println(unsetValue) // Prints: null
}
```

Download
--------
```groovy
repositories {
  maven { url 'https://jitpack.io' }
}
dependencies {
  implementation 'com.github.smeiirbekov:SharedPreferences-Delegate:1.0.0'
}
```

License
-------
MIT License

Copyright (c) 2022 Serik

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
