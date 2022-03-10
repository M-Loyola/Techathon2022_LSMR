package lsmr.example.thrifty

data class itemData(
    var item: String,
    var price: String,
)

{
    constructor(): this("","")
}