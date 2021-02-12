package by.bsuir.mark.task.fourth;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Main {

    /**
     *  Quick
     *  Validation of XML
     */
    public static void main(String[] args) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File XSDFile = new File("./src/main/resources/medicines_xsd_schema.xml");
        File XMLFile = new File("./src/test/resources/medicines.xml");
        Source XMLSource = new StreamSource(XMLFile);

        try {
            Schema schema = factory.newSchema(XSDFile);
            Validator validator = schema.newValidator();
            validator.validate(XMLSource);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
