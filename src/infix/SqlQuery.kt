package infix

import operatoroverloading.User

/**
 * 模拟数据库查询
 *
 * @author Scott Smith 2018-02-06 10:01
 */

class ColumnBuilder(var columns: Array<out String>) {

}

class Sql private constructor() {
    var columns = emptyList<String>()
    var entityClass: Class<*>? = null
    var condition: String? = null

    companion object {
        fun get(): Sql {
            return Sql()
        }
    }

    infix fun select(columnBuilder: ColumnBuilder): Sql {
        this.columns = columnBuilder.columns.asList()
        return this
    }

    infix fun from(entityClass: Class<*>): Sql {
        this.entityClass = entityClass
        return this
    }

    infix fun where(condition: String): Sql {
        this.condition = condition
        return this
    }

    fun <T> query(): T {
        // 此处省略所有条件判断
        val sqlBuilder = StringBuilder("select ")

        val columnBuilder = StringBuilder("")
        if(columns.size == 1 && columns[0] == "*") {
            columnBuilder.append("*")
        } else {
            columns.forEach {
                columnBuilder.append(it).append(",")
            }
            columnBuilder.delete(columns.size - 1, columns.size)
        }

        val sql = sqlBuilder.append(columnBuilder.toString())
                            .append(" from ${entityClass?.simpleName} where ")
                            .append(condition)
                            .toString()
        println("执行SQL查询：$sql")

        return execute(sql)
    }

    private fun <T> execute(sql: String): T {
        // 仅仅用于测试
        return Any() as T
    }
}

fun database(init: Sql.()->Unit) {
    init.invoke(Sql.get())
}

fun columns(vararg columns: String): ColumnBuilder {
    return ColumnBuilder(columns)
}

fun main(args: Array<String>) {
    database {
        (select (columns("*")) from User::class.java where "age > 18").query()
    }
}