package by.bsuir.mark.task.fourth.validation;

import org.junit.Assert;
import org.junit.Test;

public class XMLValidatorTest {

    private static final String XSD_PATH = "./src/main/resources/medicines_xsd_schema.xml";

    private static final String CORRECT_XML_PATH = "./src/test/resources/correct_medicines.xml";
    private static final String INCORRECT_XML_PATH = "./src/test/resources/incorrect_medicines.xml";

    private final XmlValidator xmlValidator = new XmlValidator(XSD_PATH);

    @Test
    public void testIsValidShouldReturnTrueWhenCorrectXmlFileProvided(){
        // given

        // when
        boolean result = xmlValidator.isValid(CORRECT_XML_PATH);
        // then
        Assert.assertTrue(result);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenIncorrectXmlFileProvided(){
        // given

        // when
        boolean result = xmlValidator.isValid(INCORRECT_XML_PATH);
        // then
        Assert.assertFalse(result);
    }
}
