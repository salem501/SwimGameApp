package entity

/**
 * ein Enum, das alle möglichen Aktionen enthält
 */
enum class Action {
    SWAP_ONE,
    SWAP_ALL,
    PASS,
    KNOCK,
    NO_ACTION,
    ;
    override fun toString() = when(this) {
        SWAP_ONE -> "swapped one"
        SWAP_ALL -> "swapped all"
        PASS -> "passed"
        KNOCK -> "knocked"
        NO_ACTION -> ""
    }
}