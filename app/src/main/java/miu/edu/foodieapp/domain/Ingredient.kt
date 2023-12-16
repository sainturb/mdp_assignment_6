package miu.edu.foodieapp.domain

class Ingredient constructor(id: Int, name: String, amount: Int, measure: String) {
    var id: Int = id
    var name: String = name
    val amount: Int = amount
    val measure: String = measure


    override fun toString(): String {
        return "$id $name $amount $measure"
    }
}