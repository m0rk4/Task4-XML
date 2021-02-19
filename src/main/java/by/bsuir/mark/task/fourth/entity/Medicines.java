package by.bsuir.mark.task.fourth.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "medicines")
public class Medicines {

    @XmlElementRef(name = "medicines", type = JAXBElement.class)
    protected List<Medicine> medicines;

    public List<Medicine> getMedicines() {
        return medicines;
    }

}
