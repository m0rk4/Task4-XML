package by.bsuir.mark.task.fourth.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static final Logger LOGGER = LogManager.getLogger(XmlValidator.class);

    private final String xsdPath;

    public XmlValidator(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    public boolean isValid(String xmlPath) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File xsdFile = new File(xsdPath);
        File xmlFile = new File(xmlPath);
        StreamSource streamSource = new StreamSource(xmlFile);

        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(streamSource);
            LOGGER.info("IN isValid - XML file: {} successfully validated", xmlPath);
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error("IN isValid - XML file: {} doesn't fit XSD file: {}", xmlPath, xsdPath, e);
            return false;
        }
    }
}
