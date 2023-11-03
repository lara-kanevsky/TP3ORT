package com.example.bdt_global.entities.viewEntities

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.bdt_global.R.string
import com.example.bdt_global.entities.Option
import com.example.bdt_global.entities.NumberInput

class EditTextAnswerBox(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), AnswerBox {

    override fun isCompleted(): Boolean {
        var isCompleted = true
        var i = 0
        var editTexts = children.toList()
        while (i < editTexts.size && isCompleted) {
            if ((editTexts[i] as EditText).text.isEmpty()) {
                isCompleted = false
            }
            i++
        }
        return isCompleted
    }

    override fun checkCompleted(): Boolean {
        val isCompleted = isCompleted()
        if (!isCompleted) {
            throw Error(context.getString(string.error_edit_text_answer_box_is_completed))
        }
        return isCompleted
    }

    override fun getAnswers(): List<Option> {
        checkCompleted()
        return children.toList().map { buildNumberInput(it as EditText) }
    }

    private fun buildNumberInput(option: EditText): NumberInput {
        val type = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_key_type)] as String
        val optionId = option.id
        val label = option.hint.toString()
        val value =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_key_value)] as Float
        val nextScreenNavigationId = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_key_next_screen_navigation_id)] as String
        val nextDefaultScreenNavigationId = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_key_next_default_screen_navigation_id)] as String
        val screenId =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_key_screen_id)] as Int
        val measureUnit = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_key_measure_unit)] as String
        val userInput = option.text.toString().toFloat()

        return NumberInput(type, optionId, label, value, nextScreenNavigationId, nextDefaultScreenNavigationId, screenId, measureUnit, userInput.toString())
    }

}
