package by.bsuir.mark.task.fourth.creation;

import by.bsuir.mark.task.fourth.entity.*;
import by.bsuir.mark.task.fourth.parsing.Parser;
import by.bsuir.mark.task.fourth.parsing.ParserException;
import by.bsuir.mark.task.fourth.validation.XmlValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class DirectorTest {

    private static final String CORRECT_XML_PATH = "./src/test/resources/correct_medicines.xml";
    private static final String INCORRECT_XML_PATH = "./src/test/resources/incorrect_medicines.xml";

    private static final List<Medicine> EXPECTED_MEDICINE_VALID = Arrays.asList(
            new Medicine("CoolPills", Group.BAA, 100, new Manufacturer("Andrew Corp", "231002")),
            new Medicine("ExtraMed", Group.VITAMINS, 500000, new Manufacturer("Piller", "231009")),
            new LiquidMedicine("LiquidMed", Group.OTHER, 23442, new Manufacturer("Mark Corp", "231103"), 999),
            new LiquidMedicine("LiquidMedExtra", Group.PAINKILLERS, 55, new Manufacturer("MarkCorporative", "202022"), 500),
            new PillMedicine("PillMed", Group.OTHER, 1337, new Manufacturer("Markysha Corp", "233223"), 20, true),
            new PillMedicine("PillMedLovely", Group.ANTIBIOTICS, 4044, new Manufacturer("Pharmacy Land", "727190"), 50, false)
    );

    private XmlValidator xmlValidator;
    private Parser parser;
    private Director director;

    @Before
    public void setup() throws ParserException {
        xmlValidator = Mockito.mock(XmlValidator.class);
        when(xmlValidator.isValid(CORRECT_XML_PATH)).thenReturn(true);
        when(xmlValidator.isValid(INCORRECT_XML_PATH)).thenReturn(false);

        parser = Mockito.mock(Parser.class);
        when(parser.parse(CORRECT_XML_PATH)).thenReturn(EXPECTED_MEDICINE_VALID);
        when(parser.parse(INCORRECT_XML_PATH)).thenThrow(new ParserException());

        director = new Director(xmlValidator, parser);
    }

    @Test
    public void testParseShouldReturnOptionalContainingExpectedListWhenCorrectXmlProvided() {
        // given

        // when
        Optional<List<Medicine>> result = director.parse(CORRECT_XML_PATH);
        //then
        Assert.assertEquals(Optional.of(EXPECTED_MEDICINE_VALID), result);
    }

    @Test
    public void testParseShouldEmptyOptionalWhenIncorrectXmlProvided() {
        // given

        // when
        Optional<List<Medicine>> result = director.parse(INCORRECT_XML_PATH);
        //then
        Assert.assertEquals(Optional.empty(), result);
    }
}
