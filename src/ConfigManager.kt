import org.w3c.dom.Document
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class ConfigManager
{
    private val path: String = "config.xml"
    private var xmlDoc : Document

    init
    {
        val xmlFile = File(path)
        xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile)
    }

    fun readFromConfig(item : String) : String
    {
        val taggedItems : NodeList = xmlDoc.getElementsByTagName(item)

        if (taggedItems.length > 1 || taggedItems == null)
        {
            //ToDo: Fehlermeldung
        }

        return taggedItems.item(0).nodeValue
    }
}