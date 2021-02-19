package by.bsuir.mark.task.fourth.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medicineType")
@XmlSeeAlso({
        LiquidMedicine.class,
        PillMedicine.class
})
public class Medicine {
    @XmlElement(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "group")
    protected Group group;
    @XmlElement(name = "price", required = true)
    protected int price;
    @XmlElement(name = "manufacturer", required = true)
    protected Manufacturer manufacturer;

    public Medicine() {}

    public Medicine(String name, Group group, int price, Manufacturer manufacturer) {
        this.name = name;
        this.group = group;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public int getPrice() {
        return price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Medicine medicine = (Medicine) object;
        return price == medicine.price &&
                (name == medicine.name || (name != null && name.equals(medicine.name))) &&
                group == medicine.group &&
                (manufacturer == medicine.manufacturer ||
                        (manufacturer != null && manufacturer.equals(medicine.manufacturer)));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (group == null ? 0 : group.hashCode());
        result = 31 * result + (manufacturer == null ? 0 : manufacturer.hashCode());
        result = 31 * result + Integer.hashCode(price);
        return result;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", group=" + group +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
