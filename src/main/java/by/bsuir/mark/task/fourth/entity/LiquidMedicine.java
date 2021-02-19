package by.bsuir.mark.task.fourth.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "liquidMedicine", propOrder = {"volume"})
public class LiquidMedicine extends Medicine {

    @XmlElement(name = "volume", required = true)
    protected int volume;

    public LiquidMedicine() {}

    public LiquidMedicine(String name, Group group, int price, Manufacturer manufacturer, int volume) {
        super(name, group, price, manufacturer);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        LiquidMedicine that = (LiquidMedicine) object;
        return volume == that.volume;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + super.hashCode();
        result = 31 * result + Integer.hashCode(volume);
        return result;
    }

    @Override
    public String toString() {
        return "LiquidMedicine{" +
                "volume=" + volume +
                '}';
    }
}
