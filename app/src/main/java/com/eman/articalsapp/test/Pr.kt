package com.eman.articalsapp.test

var setterVisibility: String = "abc"
    private set

open class Pr {
    open protected val i = 0

    open fun iValue(): String {
        setterVisibility = "10"
        return setterVisibility
    }

    fun iValue(i: Int): Int {
        return i
    }


}
