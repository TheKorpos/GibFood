package hu.bme.aut.lab.gibfood.util

import java.util.concurrent.Executor

class MainThreadExecutor : Executor {
    override fun execute(command: Runnable?) {
        command?.run()
    }
}