package by.bsuir.mark.task.fourth.creation;

import by.bsuir.mark.task.fourth.entity.Medicine;
import by.bsuir.mark.task.fourth.parsing.Parser;
import by.bsuir.mark.task.fourth.parsing.ParserException;
import by.bsuir.mark.task.fourth.validation.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Director {

    private final static Logger LOGGER = LogManager.getLogger(Director.class);
    private final XmlValidator validator;
    private final Parser parser;

    public Director(XmlValidator validator, Parser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public Optional<List<Medicine>> parse(String xmlFile) {
        try {
            boolean isValid = validator.isValid(xmlFile);
            if (isValid){
                List<Medicine> plants = parser.parse(xmlFile);
                return Optional.of(plants);
            }
        } catch (ParserException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }
}
