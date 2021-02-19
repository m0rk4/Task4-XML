package by.bsuir.mark.task.fourth.parsing;

import by.bsuir.mark.task.fourth.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MedicineDOMParserTest {
    private final Parser domParser = new MedicineDOMParser();

    private static final String CORRECT_XML_PATH = "./src/test/resources/correct_medicines.xml";
    private static final List<Medicine> EXPECTED_MEDICINE_VALID = Arrays.asList(
            new Medicine("CoolPills", Group.BAA, 100, new Manufacturer("Andrew Corp", "231002")),
            new Medicine("ExtraMed", Group.VITAMINS, 500000, new Manufacturer("Piller", "231009")),
            new LiquidMedicine("LiquidMed", Group.OTHER, 23442, new Manufacturer("Mark Corp", "231103"), 999),
            new LiquidMedicine("LiquidMedExtra", Group.PAINKILLERS, 55, new Manufacturer("MarkCorporative", "202022"), 500),
            new PillMedicine("PillMed", Group.OTHER, 1337, new Manufacturer("Markysha Corp", "233223"), 20, true),
            new PillMedicine("PillMedLovely", Group.ANTIBIOTICS, 4044, new Manufacturer("Pharmacy Land", "727190"), 50, false)
    );

    @Test
    public void testParseShouldReturnListOfMedicinesWhenCorrectPathProvided() throws ParserException {
        // given

        // when
        List<Medicine> result = domParser.parse(CORRECT_XML_PATH);
        // then
        Assert.assertEquals(EXPECTED_MEDICINE_VALID, result);
    }

}
