package hu.bme.aut.lab.gibfood.model

import com.google.gson.annotations.SerializedName
import java.util.*


class Credential {

    @SerializedName("username")
    var username: String? = null

    @SerializedName("password")
    var password: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val credential =
            o as Credential
        return username == credential.username &&
                password == credential.password
    }

    override fun hashCode(): Int {
        return Objects.hash(username, password)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Credential {\n")
        sb.append("    username: ").append(toIndentedString(username)).append("\n")
        sb.append("    password: ").append(toIndentedString(password)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}