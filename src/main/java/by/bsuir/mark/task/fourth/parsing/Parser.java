package by.bsuir.mark.task.fourth.parsing;

import by.bsuir.mark.task.fourth.entity.Medicine;

import java.util.List;

public interface Parser {
    List<Medicine> parse(String path) throws ParserException;
}
