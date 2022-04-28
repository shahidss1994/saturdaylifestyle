package com.shock.saturdaylifestyle.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.DialogDateBinding
import java.util.*

class DatePickerDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "DatePickerDialogFragment"
    }

    private var dateSelectedListener: DateSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DialogDateBinding.inflate(inflater, container, false).apply {
            val calendar = Calendar.getInstance()
            datePicker.maxDate = calendar.timeInMillis
            datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ) { view, year, monthOfYear, dayOfMonth ->
                dateSelectedListener?.onDateSelected(year, monthOfYear, dayOfMonth)
            }
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DatePicker)
    }

    fun show(supportFragmentManager: FragmentManager, dateSelectedListener: DateSelectedListener) {
        show(
            supportFragmentManager,
            TAG
        )
        this.dateSelectedListener = dateSelectedListener
    }

    interface DateSelectedListener {
        fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int)
    }


}