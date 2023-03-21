import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    var fohEmployees: Int by mutableStateOf(0)
    var bohEmployees: Int by mutableStateOf(0)
    var fohEmployeeTipAmount: Double by mutableStateOf(0.0)
    var bohEmployeeTipAmount: Double by mutableStateOf(0.0)
    var totalTipsTextAreaString: String by mutableStateOf("")
    var percentageString: String by mutableStateOf("")

    renderComposable(rootElementId = "root") {
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("Total tips:")
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("$")
            }
            Span({ style { padding(15.px) } }) {
                TextArea {
                    this.onInput {
                        totalTipsTextAreaString = it.value
                    }
                }
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("Number of FOH employees:")
            }
        }
        Div({ style { padding(12.px) } }) {
            Button(attrs = {
                onClick { fohEmployees -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$fohEmployees")
            }

            Button(attrs = {
                onClick { fohEmployees += 1 }
            }) {
                Text("+")
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("Number of BOH employees:")
            }
        }
        Div({ style { padding(12.px) } }) {
            Button(attrs = {
                onClick { bohEmployees -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$bohEmployees")
            }

            Button(attrs = {
                onClick { bohEmployees += 1 }
            }) {
                Text("+")
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("FOH tip percentage more:")
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                TextArea {
                    this.onInput {
                        percentageString = it.value
                    }
                }
            }
            Span({ style { padding(15.px) } }) {
                Text("%")
            }
        }
        Div({ style { padding(12.px) } }) {
            Button(attrs = {
                onClick {
                    val totalTipsFromString = totalTipsTextAreaString.toDouble()
                    val percentageFromString = percentageString.toDouble() / 100
                    bohEmployeeTipAmount =
                        totalTipsFromString / (((percentageFromString + 1) * fohEmployees) + bohEmployees)
                    fohEmployeeTipAmount = bohEmployeeTipAmount * (1 + percentageFromString)
                }
            }) {
                Text("Calculate")
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("FOH tips per employee:")
            }
            Span({ style { padding(0.px) } }) {
                Text("$")
            }
            Span({ style { padding(0.px) } }) {
                val text = fohEmployeeTipAmount.asDynamic().toFixed(2) as String
                Text("$text")
            }
        }
        Div({ style { padding(12.px) } }) {
            Span({ style { padding(15.px) } }) {
                Text("BOH tips per employee:")
            }
            Span({ style { padding(0.px) } }) {
                Text("$")
            }
            Span({ style { padding(0.px) } }) {
                val text = bohEmployeeTipAmount.asDynamic().toFixed(2) as String
                Text("$text")
            }
        }
    }
}

