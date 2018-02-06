package operatoroverloading

/**
 * 一元操作符
 *
 * @author Scott Smith 2018-02-03 14:11
 */
class Number(var value: Int) {
    override fun toString(): String {
        return "Number value = $value"
    }
}

/**
 * 重载一元操作符+，使其对Number中实际数据取绝对值
 */
operator fun Number.unaryPlus(): Number {
    this.value = Math.abs(value)
    return this
}

/**
 * 重载一元操作符-，使其对Number中实际数据取反
 */
operator fun Number.unaryMinus(): Number {
    this.value = -value
    return this
}

/**
 * 这个操作符通常是用于逻辑取反，这里用一个没有意义的操作，来模拟重载这个操作符
 * 结果：始终返回Number中实际数据的负值
 */
operator fun Number.not(): Number {
    this.value = -Math.abs(value)
    return this
}

operator fun Number.inc(): Number {
    return Number(this.value + 1)
}

fun main(args: Array<String>) {
    var number = Number(-3)
    println(number)
    println("After unaryPlus: ${+number}")
    println("After unaryMinus: ${-number}")

    number.value = Math.abs(number.value)
    println("After unaryNot: ${!number}")

    println("After increment(a++): ${number++}")
    println("Now $number")
    println("After increment(++a): ${++number}")
}