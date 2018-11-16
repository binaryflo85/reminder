import java.time.LocalDateTime
import com.github.caldav4j.CalDAVCollection
import com.github.caldav4j.model.request.CalendarQuery
import com.github.caldav4j.util.GenerateQuery
import net.fortuna.ical4j.model.WeekDay
import sun.net.www.http.HttpClient

class TermController(val topic : Topic,val configManager : ConfigManager)
{
    private var term : Term? = null

    init
    {
        buildTerm(topic)
    }

    private fun buildTerm(topic : Topic)
    {
        val timeSlot = getNextFreeTimeSlot()

        term = Term(topic,timeSlot.start_time,timeSlot.period)
    }

    private fun getNextFreeTimeSlot() : TimeSlot
    {
        val httpClient = HttpClient()

        val calDav = CalDAVCollection(configManager.readFromConfig("URI"))

        val genQuery = GenerateQuery()

        //genQuery.setFilter()
        val calQuery : CalendarQuery = genQuery.generate()

        calDav.queryCalendars(httpClient, calQuery)
//ToDo: Algorithmus für die Ermittlung der Definition freier Zeiträume neu definieren
//ToDo: Hierfür eigenes Objekt verwenden????
        val timeSlot = TimeSlot(LocalDateTime.now(),15)

        return timeSlot
    }

    public fun putToCalendar()
    {
        if (term != null)
        {
            // ToDo: CalDavKlasse benutzen um Termin zuzuweisen
        }
    }
}