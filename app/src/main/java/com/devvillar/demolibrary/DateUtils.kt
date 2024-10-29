package com.devvillar.demolibrary
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        // Método para convertir una fecha a un formato amigable
        fun formatToFriendlyDate(date: Date): String {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return dateFormat.format(date)
        }

        // Método para calcular los días entre dos fechas
        fun daysBetween(startDate: Date, endDate: Date): Long {
            val differenceInMillis = endDate.time - startDate.time
            return differenceInMillis / (1000 * 60 * 60 * 24)
        }

        // Método para obtener la fecha actual en un formato amigable
        fun getCurrentDateFriendly(): String {
            val currentDate = Date()
            return formatToFriendlyDate(currentDate)
        }
    }
}
