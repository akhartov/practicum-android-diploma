package ru.practicum.android.diploma.util

import android.content.Context
import ru.practicum.android.diploma.R

class SalaryMapper(
    val context: Context,
    val currencyMapper: CurrencyMapper
) {
    // все элементы соотвествуют классу SalaryDto
    fun getSalaryInfo(
        from: Int? = null,
        to: Int? = null,
        currency: String
    ): String{
        // нижняя граница зарплаты
        val limitFrom = formatLimit(
            context.getString(R.string.from),
            from
        )
        // верхняя граница зарплаты
        val limitTo = formatLimit(
            context.getString(R.string.to),
            to
        )
        val currencySymbol = currencyMapper.codeToSymbol(currency)
        return  if(limitTo.isEmpty() && limitFrom.isEmpty()){
            context.getString(R.string.no_salary)
        } else if (limitTo.isEmpty()){
             SAMPLE_ONE_LIMIT.format(limitFrom, currencySymbol)
        } else if (limitFrom.isEmpty()) {
            SAMPLE_ONE_LIMIT.format(limitTo, currencySymbol)
        } else {
            SAMPLE_TWO_LIMIT.format(limitFrom, limitTo, currencySymbol)
        }
    }

    // форматирование одной границы
    private fun formatLimit(limit: String, number: Int?): String{
        return if (number != null){
            limit + SAMPLE_NUMBER.format(number)
        } else {
            ""
        }
    }

    companion object{
        const val SAMPLE_NUMBER = " %d"
        const val SAMPLE_ONE_LIMIT = "%s %s"
        const val SAMPLE_TWO_LIMIT = "%s %s %s"
    }
}
