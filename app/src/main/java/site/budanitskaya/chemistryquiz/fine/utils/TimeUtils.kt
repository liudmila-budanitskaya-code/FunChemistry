package site.budanitskaya.chemistryquiz.fine.utils

object TimeUtils {

    fun formatTimeSavedInPreference(time: String?): String {
        if (time == null) return ""

        try {
            val split = time.split(":")
            var hour: Int = split[0].toInt()
            val minute: Int = split[1].toInt()
            val timeSet: String

            when {
                hour > 12 -> {
                    hour -= 12
                    timeSet = "PM"
                }
                hour == 0 -> {
                    hour += 12
                    timeSet = "AM"
                }
                hour == 12 -> {
                    timeSet = "PM"
                }
                else -> {
                    timeSet = "AM"
                }
            }

            return "$hour:${if (minute < 10) "0" else ""}$minute $timeSet"
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            return ""
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
            return ""
        }
    }
}