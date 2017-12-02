package com.fpinkotlin.optionaldata.exercise11

import com.fpinkotlin.common.sum
import com.fpinkotlin.generators.IntListGenerator
import com.fpinkotlin.generators.forAll
import io.kotlintest.specs.StringSpec

class OptionTest: StringSpec() {

    init {

        "sequenceSome" {
            forAll(IntListGenerator(), { (array, list) ->
                sequence(list.map { Option(it)}).map { lst -> sum(lst)} ==
                        Option(array.sum())
            })
        }

        "sequenceNone" {
            val f: (Int) -> Option<Int> = { a -> if (a % 2 == 0 ) Option(a) else Option() }
            forAll(IntListGenerator(), { (array, list) ->
                sequence(list.map(f)).map { lst -> sum(lst)} == Option<Int>() ||
                        array.map { a -> if (a % 2 == 0 ) a else 0 }.sum() == array.sum()
            })
        }
    }
}