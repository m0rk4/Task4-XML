package by.bsuir.mark.task.fourth.entity;

public class PillMedicine extends Medicine {
    private int totalPills;
    private boolean isCapsule;

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
