package by.bsuir.mark.task.fourth.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medManufacturer")
public class Manufacturer {
    @XmlElement(name = "name", required = true)
    protected String name;
    @XmlElement(name = "zip", required = true)
    protected String zip;

    public Manufacturer() {}

    public Manufacturer(String name, String zip) {
        this.name = name;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public String getZip() {
        return zip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Manufacturer manufacturer = (Manufacturer) object;
        return (name == manufacturer.name || (name != null && name.equals(manufacturer.name)))
                && (zip == manufacturer.zip || (zip != null && zip.equals(manufacturer.zip)));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (zip == null ? 0 : zip.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "name='" + name + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
