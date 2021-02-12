package by.bsuir.mark.task.fourth.entity;

public class LiquidMedicine extends Medicine {
    private int volume;

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
