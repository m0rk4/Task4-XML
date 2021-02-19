package by.bsuir.mark.task.fourth.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pillMedicine", propOrder = {"totalPills", "isCapsule"})
public class PillMedicine extends Medicine {
    @XmlElement(name = "total-pills", required = true)
    protected int totalPills;
    @XmlElement(name = "is-capsule", required = true)
    protected boolean isCapsule;

    public PillMedicine() {}

    public PillMedicine(String name, Group group, int price, Manufacturer manufacturer, int totalPills, boolean isCapsule) {
        super(name, group, price, manufacturer);
        this.totalPills = totalPills;
        this.isCapsule = isCapsule;
    }

    public int getTotalPills() {
        return totalPills;
    }

    public boolean isCapsule() {
        return isCapsule;
    }

    public void setTotalPills(int totalPills) {
        this.totalPills = totalPills;
    }

    public void setCapsule(boolean capsule) {
        isCapsule = capsule;
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
        PillMedicine that = (PillMedicine) object;
        return totalPills == that.totalPills &&
                isCapsule == that.isCapsule;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + super.hashCode();
        result = 31 * result + Integer.hashCode(totalPills);
        result = 31 * result + Boolean.hashCode(isCapsule);
        return result;
    }

    @Override
    public String toString() {
        return "PillMedicine{" +
                "totalPills=" + totalPills +
                ", isCapsule=" + isCapsule +
                '}';
    }
}
