package operatoroverloading

/**
 * 二元操作符
 *
 * @author Scott Smith 2018-02-05 12:19
 */

// 算术运算符
operator fun Number.plus(value: Int): Number {
    return Number(this.value + value)
}

operator fun Number.rangeTo(to: Number): IntRange {
    return this.value..to.value
}

class IntCollection {
    val intList = ArrayList<Int>()

    fun add(vararg value: Int) {
        intList.addAll(value.asList())
    }
}

// 重载"in"操作符
operator fun IntCollection.contains(value: Int): Boolean {
    return this.intList.contains(value)
}

// 重载a[i]操作符
operator fun IntCollection.get(index: Int): Int {
    return intList[index]
}

// 重载a[i] = b操作符
operator fun IntCollection.set(index: Int, value: Int) {
    intList[index] = value
}

open class User(var name: String?,
           var age: Int?) {

//    operator override fun equals(other: Any?): Boolean {
//        if(other is User) {
//            return (this.name == other.name) && (this.age == other.age)
//        }
//        return false
//    }

}

operator fun User.get(key: String): Any? {
    when(key) {
        "name" -> {
            return this.name
        }
        "age" -> {
            return this.age
        }
    }

    return null
}

operator fun User.set(key: String, value:Any?) {
    when(key) {
        "name" -> {
            name = value as? String
        }
        "age" -> {
            age = value as? Int
        }
    }
}

//class JsonParser {
//
//}
//
//operator fun JsonParser.invoke(json: String): Map<String, Any> {
//    val map = Json.parse(json)
//
//    return map
//}

// 广义赋值运算符
operator fun Number.plusAssign(value: Int) {
    this.value += value
}

// 比较操作符
operator fun Number.compareTo(number: Number): Int {
    return this.value - number.value
}

// 获取两个集合的交集
infix fun <E> List<E>.n(other: List<E>): List<E> {
    val result = ArrayList<E>()
    forEach {
        if(other.contains(it)) {
            result.add(it)
        }
    }

    return result
}

fun main(args: Array<String>) {
    val startNumber = Number(3)
    val endNumber = Number(9)

    (startNumber..endNumber).forEach {
        println("value = $it")
    }

    println((Number(1) + 2))

    val intCollection = IntCollection()
    intCollection.add(1, 2, 3)
    println(3 in intCollection)

    println(intCollection[0])

    intCollection[2] = 4
    println(intCollection[2])

    val user = User("Scott Smith", 18)
    println(user["name"])
    user["age"] = 22
    println(user["age"])

//    val parser = JsonParser()
//    parser.invoke("{name: \"Scott Smith\"}")

    val number = Number(1)
    number += 2
    println(number)

    println(Number(3) > Number(5))

    val list1 = listOf<Int>(2, 1, 3, 4)
    val list2 = listOf<Int>(3, 5, 6, 4)

    (list1 n list2).forEach {
        println("it = $it")
    }
}