package ru.haliksar.flowapp.libraries.network.converters

import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.internal.bind.util.ISO8601Utils
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.util.*
import kotlin.math.pow

/*
Thanks Moshi!
*/
class Rfc3339DateJsonAdapter : TypeAdapter<Date?>() {
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) {
            out.nullValue()
        } else {
            val date: String = ISO8601Utils.format(value)
            out.value(date)
        }
    }

    override fun read(`in`: JsonReader): Date? {
        if (`in`.peek() === JsonToken.NULL) {
            `in`.nextNull()
            return null
        }

        val dateAsString = `in`.nextString()
        return Iso8601Utils.parse(dateAsString)
    }
}


/*Thanks Moshi!*/
internal object Iso8601Utils {
    /** ID to represent the 'GMT' string  */
    private const val GMT_ID = "GMT"

    /** The GMT timezone, prefetched to avoid more lookups.  */
    private val TIMEZONE_Z = TimeZone.getTimeZone(GMT_ID)

    /** Returns `date` formatted as yyyy-MM-ddThh:mm:ss.sssZ  */
    fun format(date: Date): String {
        val calendar: Calendar =
            GregorianCalendar(TIMEZONE_Z, Locale.US)
        calendar.time = date

        // estimate capacity of buffer as close as we can (yeah, that's pedantic ;)
        val capacity = "yyyy-MM-ddThh:mm:ss.sssZ".length
        val formatted = StringBuilder(capacity)
        padInt(formatted, calendar[Calendar.YEAR], "yyyy".length)
        formatted.append('-')
        padInt(formatted, calendar[Calendar.MONTH] + 1, "MM".length)
        formatted.append('-')
        padInt(formatted, calendar[Calendar.DAY_OF_MONTH], "dd".length)
        formatted.append('T')
        padInt(formatted, calendar[Calendar.HOUR_OF_DAY], "hh".length)
        formatted.append(':')
        padInt(formatted, calendar[Calendar.MINUTE], "mm".length)
        formatted.append(':')
        padInt(formatted, calendar[Calendar.SECOND], "ss".length)
        formatted.append('.')
        padInt(formatted, calendar[Calendar.MILLISECOND], "sss".length)
        formatted.append('Z')
        return formatted.toString()
    }

    /**
     * Parse a date from ISO-8601 formatted string. It expects a format
     * [yyyy-MM-dd|yyyyMMdd][T(hh:mm[:ss[.sss]]|hhmm[ss[.sss]])]?[Z|[+-]hh:mm]]
     *
     * @param date ISO string to parse in the appropriate format.
     * @return the parsed date
     */
    fun parse(date: String): Date {
        return try {
            var offset = 0

            // extract year
            val year = parseInt(date, offset, 4.let { offset += it; offset })
            if (checkOffset(date, offset, '-')) {
                offset += 1
            }

            // extract month
            val month = parseInt(date, offset, 2.let { offset += it; offset })
            if (checkOffset(date, offset, '-')) {
                offset += 1
            }

            // extract day
            val day = parseInt(date, offset, 2.let { offset += it; offset })
            // default time value
            var hour = 0
            var minutes = 0
            var seconds = 0
            var milliseconds =
                0 // always use 0 otherwise returned date will include millis of current time

            // if the value has no time component (and no time zone), we are done
            val hasT = checkOffset(date, offset, 'T')
            if (!hasT && date.length <= offset) {
                val calendar: Calendar = GregorianCalendar(year, month - 1, day)
                return calendar.time
            }
            if (hasT) {

                // extract hours, minutes, seconds and milliseconds
                hour = parseInt(
                    date,
                    1.let { offset += it; offset },
                    2.let { offset += it; offset }
                )
                if (checkOffset(date, offset, ':')) {
                    offset += 1
                }
                minutes = parseInt(date, offset, 2.let { offset += it; offset })
                if (checkOffset(date, offset, ':')) {
                    offset += 1
                }
                // second and milliseconds can be optional
                if (date.length > offset) {
                    val c = date[offset]
                    if (c != 'Z' && c != '+' && c != '-') {
                        seconds =
                            parseInt(date, offset, 2.let { offset += it; offset })
                        if (seconds in 60..62) seconds =
                            59 // truncate up to 3 leap seconds
                        // milliseconds can be optional in the format
                        if (checkOffset(date, offset, '.')) {
                            offset += 1
                            val endOffset = indexOfNonDigit(
                                date,
                                offset + 1
                            ) // assume at least one digit
                            val parseEndOffset =
                                endOffset.coerceAtMost(offset + 3) // parse up to 3 digits
                            val fraction = parseInt(date, offset, parseEndOffset)
                            milliseconds =
                                (10.0.pow(3 - (parseEndOffset - offset).toDouble()) * fraction).toInt()
                            offset = endOffset
                        }
                    }
                }
            }

            // extract timezone
            require(date.length > offset) { "No time zone indicator" }
            val timezone: TimeZone
            val timezoneIndicator = date[offset]
            if (timezoneIndicator == 'Z') {
                timezone = TIMEZONE_Z
            } else if (timezoneIndicator == '+' || timezoneIndicator == '-') {
                val timezoneOffset = date.substring(offset)
                // 18-Jun-2015, tatu: Minor simplification, skip offset of "+0000"/"+00:00"
                if ("+0000" == timezoneOffset || "+00:00" == timezoneOffset) {
                    timezone = TIMEZONE_Z
                } else {
                    // 18-Jun-2015, tatu: Looks like offsets only work from GMT, not UTC...
                    //    not sure why, but it is what it is.
                    val timezoneId = GMT_ID + timezoneOffset
                    timezone = TimeZone.getTimeZone(timezoneId)
                    val act = timezone.id
                    if (act != timezoneId) {
                        /* 22-Jan-2015, tatu: Looks like canonical version has colons, but we may be given
                 *    one without. If so, don't sweat.
                 *   Yes, very inefficient. Hopefully not hit often.
                 *   If it becomes a perf problem, add 'loose' comparison instead.
                 */
                        val cleaned = act.replace(":", "")
                        if (cleaned != timezoneId) {
                            throw IndexOutOfBoundsException(
                                "Mismatching time zone indicator: "
                                        + timezoneId + " given, resolves to " + timezone.id
                            )
                        }
                    }
                }
            } else {
                throw IndexOutOfBoundsException(
                    "Invalid time zone indicator '$timezoneIndicator'"
                )
            }
            val calendar: Calendar = GregorianCalendar(timezone)
            calendar.isLenient = false
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month - 1
            calendar[Calendar.DAY_OF_MONTH] = day
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minutes
            calendar[Calendar.SECOND] = seconds
            calendar[Calendar.MILLISECOND] = milliseconds
            calendar.time
            // If we get a ParseException it'll already have the right message/offset.
            // Other exception types can convert here.
        } catch (e: IndexOutOfBoundsException) {
            throw JsonParseException("Not an RFC 3339 date: $date", e)
        } catch (e: IllegalArgumentException) {
            throw JsonParseException("Not an RFC 3339 date: $date", e)
        }
    }

    /**
     * Check if the expected character exist at the given offset in the value.
     *
     * @param value the string to check at the specified offset
     * @param offset the offset to look for the expected character
     * @param expected the expected character
     * @return true if the expected character exist at the given offset
     */
    private fun checkOffset(
        value: String,
        offset: Int,
        expected: Char
    ): Boolean {
        return offset < value.length && value[offset] == expected
    }

    /**
     * Parse an integer located between 2 given offsets in a string
     *
     * @param value the string to parse
     * @param beginIndex the start index for the integer in the string
     * @param endIndex the end index for the integer in the string
     * @return the int
     * @throws NumberFormatException if the value is not a number
     */
    @Throws(NumberFormatException::class)
    private fun parseInt(value: String, beginIndex: Int, endIndex: Int): Int {
        if (beginIndex < 0 || endIndex > value.length || beginIndex > endIndex) {
            throw NumberFormatException(value)
        }
        // use same logic as in Integer.parseInt() but less generic we're not supporting negative values
        var i = beginIndex
        var result = 0
        var digit: Int
        if (i < endIndex) {
            digit = Character.digit(value[i++], 10)
            if (digit < 0) {
                throw NumberFormatException(
                    "Invalid number: " + value.substring(
                        beginIndex,
                        endIndex
                    )
                )
            }
            result = -digit
        }
        while (i < endIndex) {
            digit = Character.digit(value[i++], 10)
            if (digit < 0) {
                throw NumberFormatException(
                    "Invalid number: " + value.substring(
                        beginIndex,
                        endIndex
                    )
                )
            }
            result *= 10
            result -= digit
        }
        return -result
    }

    /**
     * Zero pad a number to a specified length
     *
     * @param buffer buffer to use for padding
     * @param value the integer value to pad if necessary.
     * @param length the length of the string we should zero pad
     */
    private fun padInt(buffer: StringBuilder, value: Int, length: Int) {
        val strValue = value.toString()
        for (i in length - strValue.length downTo 1) {
            buffer.append('0')
        }
        buffer.append(strValue)
    }

    /**
     * Returns the index of the first character in the string that is not a digit, starting at
     * offset.
     */
    private fun indexOfNonDigit(string: String, offset: Int): Int {
        for (i in offset until string.length) {
            val c = string[i]
            if (c < '0' || c > '9') return i
        }
        return string.length
    }
}
