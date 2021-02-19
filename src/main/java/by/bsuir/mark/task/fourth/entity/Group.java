package by.bsuir.mark.task.fourth.entity;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Group {
    @XmlEnumValue("VITAMINS")
    VITAMINS,
    @XmlEnumValue("BAA")
    BAA,
    @XmlEnumValue("ANTIBIOTICS")
    ANTIBIOTICS,
    @XmlEnumValue("PAINKILLERS")
    PAINKILLERS,
    @XmlEnumValue("OTHER")
    OTHER;

    public static Group fromString(String name) {
        Group[] values = Group.values();
        for (Group value : values) {
            String valueName = value.name();
            if (valueName.equals(name)) {
                return value;
            }
        }
        return OTHER;
    }
}
