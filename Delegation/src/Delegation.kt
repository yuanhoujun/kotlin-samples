/**
 * Created by scott on 2016/12/31.
 */

interface Base {
    fun sayHi()
}

class BaseImpl : Base {
    override fun sayHi() {
        println("BaseImpl->sayHi")
    }
}

class Derived(b: Base) : Base by b

fun main(args: Array<String>) {
    val b = BaseImpl()
    val derived = Derived(b)
    derived.sayHi()
}