package by.bsuir.mark.task.fourth.parsing;

import by.bsuir.mark.task.fourth.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

import static by.bsuir.mark.task.fourth.parsing.ParsingConstants.*;

public class MedicineSAXHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(MedicineSAXHandler.class);

    private final List<Medicine> medicines = new LinkedList<>();
    private String currData;

    private Medicine activeMedicine;
    private LiquidMedicine currLiquidMedicine;
    private PillMedicine currPillMedicine;
    private Manufacturer currManufacturer;

    @Override
    public void startDocument() {
        LOGGER.info("IN - startDocument: Document processing started");
    }

    @Override
    public void endDocument() {
        LOGGER.info("IN - endDocument: Document processing ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case MEDICINE:
            case LIQUID_MEDICINE:
            case PILL_MEDICINE:
                startBuildingMedicines(qName, attributes);
                break;
            case MEDICINE_MANUFACTURER:
                currManufacturer = new Manufacturer();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case MEDICINE:
            case LIQUID_MEDICINE:
            case PILL_MEDICINE:
                medicines.add(activeMedicine);
                break;
            case MEDICINE_MANUFACTURER:
                activeMedicine.setManufacturer(currManufacturer);
                break;
            case MEDICINE_NAME:
                if (currManufacturer == null) {
                    activeMedicine.setName(currData);
                } else {
                    if (currManufacturer.getName() == null) {
                        currManufacturer.setName(currData);
                    } else {
                        activeMedicine.setName(currData);
                    }
                }
                break;
            case MANUFACTURER_ZIP:
                currManufacturer.setZip(currData);
                break;
            case MEDICINE_PRICE:
                int price = Integer.parseInt(currData);
                activeMedicine.setPrice(price);
                break;
            case LIQUID_MEDICINE_VOLUME:
                int volume = Integer.parseInt(currData);
                currLiquidMedicine.setVolume(volume);
                break;
            case PILL_MEDICINE_TOTAL_PILLS:
                int totalPills = Integer.parseInt(currData);
                currPillMedicine.setTotalPills(totalPills);
                break;
            case PILL_MEDICINE_IS_CAPSULE:
                boolean isCapsule = Boolean.parseBoolean(currData);
                currPillMedicine.setCapsule(isCapsule);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currData = new String(ch, start, length);
    }

    private void startBuildingMedicines(String medicineType, Attributes attributes) {
        String name = attributes.getValue(MEDICINE_NAME);
        String groupValue = attributes.getValue(MEDICINE_GROUP);
        Group group = Group.fromString(groupValue);

        switch (medicineType) {
            case MEDICINE:
                activeMedicine = new Medicine();
                break;
            case LIQUID_MEDICINE:
                currLiquidMedicine = new LiquidMedicine();
                activeMedicine = currLiquidMedicine;
                break;
            case PILL_MEDICINE:
                currPillMedicine = new PillMedicine();
                activeMedicine = currPillMedicine;
        }
        activeMedicine.setName(name);
        activeMedicine.setGroup(group);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }
}
