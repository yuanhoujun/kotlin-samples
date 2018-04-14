package objects

interface Moveable {

    fun move() {

    }
}

//class Outer {
//    private var a: String? = null
//
//    object c: Moveable {
//        override fun move() {
//            super.move()
//
//            // 这里出现了错误访问，IDE提示错误
//            println(a)
//        }
//    }
//}

class Outer {
    private var a: String? = null

    // 用变量c去接收object表达式
    private val c = object: Moveable {
        override fun move() {
            super.move()

            // 这里出现了错误访问，IDE提示错误
            println(a)
        }
    }

    companion object {

    }
}

object Singleton {
    fun f1() {

    }

    fun f2() {

    }
}

