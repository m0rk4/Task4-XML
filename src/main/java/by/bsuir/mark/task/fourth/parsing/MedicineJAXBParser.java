package by.bsuir.mark.task.fourth.parsing;

import by.bsuir.mark.task.fourth.entity.Medicine;
import by.bsuir.mark.task.fourth.entity.Medicines;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class MedicineJAXBParser implements Parser {
    private final String xsdPath;

    public MedicineJAXBParser(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    @Override
    public List<Medicine> parse(String path) throws ParserException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(xsdPath);
            Schema schema = schemaFactory.newSchema(schemaLocation);
            unmarshaller.setSchema(schema);

            File xmlLocation = new File(path);
            Medicines medicines = (Medicines) unmarshaller.unmarshal(xmlLocation);
            return medicines.getMedicines();
        } catch (JAXBException | SAXException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }
}
