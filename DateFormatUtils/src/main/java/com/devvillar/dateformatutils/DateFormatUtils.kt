package com.devvillar.dateformatutils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class DateFormatUtils {

    companion object {

        enum class MonthLang {
            ENGLISH, SPANISH
        }

        fun formatToDDMMYYYY(dateString: String): String? {
            return try {
                // Define el formato de entrada como "YYYY-MM-DD" o "YYYY/MM/DD"
                val inputFormatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd]")
                val date = LocalDate.parse(dateString, inputFormatter)

                // Define el formato de salida como "DD-MM-YYYY"
                val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                date.format(outputFormatter)
            } catch (e: Exception) {
                null // Retorna null si el formato de la fecha es incorrecto
            }
        }

        fun formatToYYYYMMDD(dateString: String): String? {
            return try {
                // Define el formato de entrada como "DD-MM-YYYY" o "DD/MM/YYYY"
                val inputFormatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy][dd/MM/yyyy]")
                val date = LocalDate.parse(dateString, inputFormatter)

                // Define el formato de salida como "YYYY-MM-DD"
                val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                date.format(outputFormatter)
            } catch (e: Exception) {
                null // Retorna null si el formato de la fecha es incorrecto
            }
        }

        fun formatToDDMonthYYYY(dateString: String, monthLang: MonthLang = MonthLang.ENGLISH): String? {
            return try {
                val inputFormatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd]")
                val date = LocalDate.parse(dateString, inputFormatter)

                // Listas de meses en inglés y español
                val englishMonths = listOf(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                )
                val spanishMonths = listOf(
                    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
                )

                // Selecciona el nombre del mes según el idioma
                val months = if (monthLang == MonthLang.ENGLISH) englishMonths else spanishMonths
                val monthName = months[date.monthValue - 1]

                // Retorna el formato específico según el idioma
                if (monthLang == MonthLang.ENGLISH) {
                    "$monthName ${date.dayOfMonth.toString().padStart(2, '0')}, ${date.year}"
                } else {
                    "${date.dayOfMonth.toString().padStart(2, '0')} $monthName ${date.year}"
                }
            } catch (e: Exception) {
                null // Retorna null si el formato de la fecha es incorrecto
            }
        }


    }
}