import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by scott on 2017/1/2.
 */

class Delegate {
    operator fun getValue(thisRef: Any? , property: KProperty<*>): String {
        return "Invoke getValue() , thisRef = $thisRef , property name = ${property.name}"
    }

    operator fun setValue(thisRef: Any? , property: KProperty<*> , value: String) {
        println("Invoke setValue() , thisRef = $thisRef , property name = ${property.name} , value = $value")
    }

}


class DelegatedProperty {
    var d: String by Delegate()
}

// 使用标准库实现的lazy函数，实现属性的延迟加载
private val lazyValue: String by lazy {
    println("调用该初始赋值表达式完成赋值")
    // 这里是实际赋值
    "Hello, world"
}

private object UNINITIALIZE_VALUE

class MyLazy<T>(initialize: ()->T) {
    private var value: Any? = UNINITIALIZE_VALUE
    private val initialize = initialize

    operator fun getValue(thisRef: Any? , property: KProperty<*>): T {
        if(value == UNINITIALIZE_VALUE) {
            value = initialize()
        }
        return value as T
    }

    operator fun setValue(thisRef: Any? , property: KProperty<*> , value: T) {
        this.value = value
    }
}

// 为了和标准库区分，使用__lazy命名
fun <T>  __lazy(initialize: () -> T): MyLazy<T> = MyLazy(initialize)

var lazyValue1 by __lazy {
    println("自定义lazy初始化赋值表达式被调用")
    "Hello , world"
}

var observableValue by Delegates.observable("Initial value") {  prop , old , new ->
    println("$old -> $new")
}

fun main(args: Array<String>) {
    val dp = DelegatedProperty()
    dp.d = "Value0"
    println(dp.d)

    // 仅在第一次会调用lazy方法的lambda表达式
    println(lazyValue)
    println(lazyValue)

    // 自定义延迟加载函数__lazy
    println(lazyValue1)
    lazyValue1 = "Other value"
    println(lazyValue1)

    println(observableValue)
    observableValue = "Hello"
    println(observableValue)
}