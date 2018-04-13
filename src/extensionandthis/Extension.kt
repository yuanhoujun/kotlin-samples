package extensionandthis

open class E {

}

open class E1: E() {

}

open class A {

    open fun E.f() {
        println("E.f in A")
    }

    open fun E1.f() {
        println("E1.f in A")
    }

    fun call(e: E) {
        e.f()
    }
}

class A1: A() {

    override fun E.f() {
        println("E.f in A1")
    }

    override fun E1.f() {
        println("E1.f in A1")
    }
}

fun main(args: Array<String>) {
    // a）
    A().call(E())

    // b）
    A1().call(E())

    // c）
    A().call(E())

    // d）
    A().call(E1())
}