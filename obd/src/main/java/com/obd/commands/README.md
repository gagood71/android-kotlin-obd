# Kotlin

<p>以原有 Java 類別為基礎，從零開始撰寫 Kotlin 類別，遇到任何語言轉譯上問題並記錄。</p>

# companion object 介紹

<p>在 Kotlin 中，companion object 是一個物件聲明，它在類別的內部聲明，可以包含類別的共用屬性和方法。它的作用類似於 Java 中的靜態成員，但在 Kotlin 中，類別沒有靜態成員，而是使用伴生物件來實現類似的功能。</p>

```kotlin
class MyClass {
    companion object {
        const val CONSTANT_VALUE = 42

        fun myFunction() {
            println("Hello from myFunction!")
        }
    }
}

fun main() {
    println(MyClass.CONSTANT_VALUE) // 访问伴生对象中的常量
    MyClass.myFunction() // 调用伴生对象中的方法
}
```

# val、var 介紹

<p>在 Kotlin 中，val 與 var 是用來宣告變數的兩個關鍵字。val 表示該變數是不可變的，而 var 表示該變數是可變的。</p>
<p>val 宣告的變數</p>
<p>val 宣告的變數只能在宣告時或初始化時被賦值，之後就無法再被修改。例如：</p>

```kotlin
val name = "John Doe"

// 此處會產生錯誤
name = "Jane Doe"
```

<p>val 宣告的變數在 Kotlin 中類似於 Java 中的 final 變數。</p>
<p>var 宣告的變數</p>
<p>var 宣告的變數可以在宣告時、初始化時或之後任意時候被賦值。例如：</p>

```kotlin
var age = 30

// 此處是合法的
age = 31
```

<p>在 Kotlin 中，通常建議盡量使用 val 來宣告變數。這樣可以提高程式的可讀性和可維護性。如果變數的值在程式執行過程中可能會發生變化，則可以使用 var 來宣告。</p>
<p>以下是一些使用 val 與 var 的建議：</p>
<p>val 來宣告常量。</p>
<p>用 val 來宣告資料類別的屬性。</p>
<p>用 var 來宣告用於存儲輸入或計算結果的變數。</p>

# shl 介紹

<p>shl 是 Kotlin 中的位元左移運算子。它用於將二進位表示的數值向左移動指定的位數。對於整數類型，shl 的語法如下：</p>

```kotlin
result = operand shl shift
```

<p>示例：</p>

```kotlin
val x = 8 // 二進位表示為 1000
val result = x shl 2 // 將 x 左移兩位，結果為 32（二進位表示為 100000）
```