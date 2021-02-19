package by.bsuir.mark.task.fourth.parsing;

import by.bsuir.mark.task.fourth.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MedicineDOMParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger(MedicineDOMParser.class);

    @Override
    public List<Medicine> parse(String path) throws ParserException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException(e.getMessage(), e);
        }

        Document document;
        try {
            document = documentBuilder.parse(path);
        } catch (SAXException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }

        Element documentElement = document.getDocumentElement();
        documentElement.normalize();
        NodeList childNodes = documentElement.getChildNodes();

        List<Medicine> medicines = new LinkedList<>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node medicineNode = childNodes.item(i);
            if (medicineNode.getNodeType() == Node.ELEMENT_NODE) {
                Medicine medicine = buildMedicineFromElement((Element) medicineNode);
                medicines.add(medicine);
            }
        }

        LOGGER.info("IN parse - All medicines from XML file: {} are successfully parsed", path);
        return medicines;
    }

    private Medicine buildMedicineFromElement(Element medicineElement) throws ParserException {
        String name = medicineElement.getAttribute(ParsingConstants.MEDICINE_NAME);

        String groupValue = medicineElement.getAttribute(ParsingConstants.MEDICINE_GROUP);
        Group group = Group.fromString(groupValue);

        String priceValue = getElementTextContent(medicineElement, ParsingConstants.MEDICINE_PRICE);
        int price = Integer.parseInt(priceValue);

        NodeList manufacturerNodeList = medicineElement.getElementsByTagName(ParsingConstants.MEDICINE_MANUFACTURER);
        Element manufacturerElement = (Element) manufacturerNodeList.item(0);

        String manufacturerName = getElementTextContent(manufacturerElement, ParsingConstants.MANUFACTURER_NAME);
        String zip = getElementTextContent(manufacturerElement, ParsingConstants.MANUFACTURER_ZIP);

        Manufacturer manufacturer = new Manufacturer(manufacturerName, zip);

        String tagName = medicineElement.getTagName();
        switch (tagName) {
            case ParsingConstants.MEDICINE:
                return new Medicine(name, group, price, manufacturer);
            case ParsingConstants.LIQUID_MEDICINE:
                String volumeValue = getElementTextContent(medicineElement, ParsingConstants.LIQUID_MEDICINE_VOLUME);
                int volume = Integer.parseInt(volumeValue);
                return new LiquidMedicine(name, group, price, manufacturer, volume);
            case ParsingConstants.PILL_MEDICINE:
                String totalPillsValue = getElementTextContent(medicineElement,
                        ParsingConstants.PILL_MEDICINE_TOTAL_PILLS);
                String isCapsuleValue = getElementTextContent(medicineElement,
                        ParsingConstants.PILL_MEDICINE_IS_CAPSULE);
                int totalPills = Integer.parseInt(totalPillsValue);
                boolean isCapsule = Boolean.parseBoolean(isCapsuleValue);
                return new PillMedicine(name, group, price, manufacturer, totalPills, isCapsule);
            default:
                throw new ParserException("Invalid tag found: " + tagName);
        }
    }

    private String getElementTextContent(Element element, String tagName) {
        NodeList elementsByTagName = element.getElementsByTagName(tagName);
        Node item = elementsByTagName.item(0);
        return item.getTextContent();
    }
}
