package com.example.bdt_global.entities.viewEntities

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import com.example.bdt_global.R.string
import com.example.bdt_global.entities.Option
import com.example.bdt_global.entities.StringOption

class RadioButtonAnswerBox(
    context: Context,
    attrs: AttributeSet? = null
) : RadioGroup(context, attrs), AnswerBox {

    override fun isCompleted(): Boolean {
        var isCompleted = true
        val checkedRadioButtonId = checkedRadioButtonId
        if (checkedRadioButtonId == -1) {
            isCompleted = false
        }
        return isCompleted
    }

    override fun checkCompleted(): Boolean {
        val isCompleted = isCompleted()
        if (!isCompleted) {
            throw Error(context.getString(string.radio_button_answer_box_is_completed_error))
        }
        return isCompleted
    }

    override fun getAnswers(): List<Option> {
        checkCompleted()
        val checkedRadioButtonId = checkedRadioButtonId
        val checkedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
        return listOf<Option>(buildStringOption(checkedRadioButton))
    }

    private fun buildStringOption(option: RadioButton): StringOption {
        val type = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_type_key)] as String
        val optionId = option.id
        val label = option.text.toString()
        val value =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_value_key)] as Float
        val nextScreenNavigationId = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_next_screen_navigation_id_key)] as String
        val nextDefaultScreenNavigationId = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_next_default_screen_navigation_id_key)] as String
        val screenId =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_screen_id_key)] as Int
        val measureUnit = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_measure_unit_key)] as String
        return StringOption(type, optionId, label, value, nextScreenNavigationId, nextDefaultScreenNavigationId, screenId, measureUnit)
    }

}
