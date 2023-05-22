package my.phatndt.xsmart.android.core.utils

import java.io.Serializable


data class Quadruple<out A, out B, out C, out D>(
    public val first: A,
    public val second: B,
    public val third: C,
    public val fourth: D,
) : Serializable {
        override fun toString(): String = "($first, $second, $third, $fourth)"
}