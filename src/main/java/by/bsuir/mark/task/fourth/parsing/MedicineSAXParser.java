package by.bsuir.mark.task.fourth.parsing;

import by.bsuir.mark.task.fourth.entity.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class MedicineSAXParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger(MedicineSAXHandler.class);

    @Override
    public List<Medicine> parse(String path) throws ParserException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new ParserException(e.getMessage(), e);
        }

        MedicineSAXHandler medicineSaxHandler = new MedicineSAXHandler();
        try {
            saxParser.parse(path, medicineSaxHandler);
        } catch (SAXException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }

        LOGGER.info("IN parse - All medicines from XML file: {} are successfully parsed", path);
        return medicineSaxHandler.getMedicines();
    }
}
