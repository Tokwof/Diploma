package marchenko.com.diplomameteors.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Transfer Object
 */
@DatabaseTable(tableName = "Days")
public class Day {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private Date dateTime;
    @DatabaseField(canBeNull = true, foreign = true)
    private Expedition expedition;

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", expedition=" + expedition +
                '}';
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (dateTime != null ? !dateTime.equals(day.dateTime) : day.dateTime != null) return false;
        return expedition != null ? expedition.equals(day.expedition) : day.expedition == null;

    }

    @Override
    public int hashCode() {
        int result = dateTime != null ? dateTime.hashCode() : 0;
        result = 31 * result + (expedition != null ? expedition.hashCode() : 0);
        return result;
    }
}
