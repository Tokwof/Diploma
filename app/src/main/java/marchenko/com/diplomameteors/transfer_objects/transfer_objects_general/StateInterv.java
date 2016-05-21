package marchenko.com.diplomameteors.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "States")
public class StateInterv {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private double startLimitMagnitude;
    @DatabaseField
    private int startMood;
    @DatabaseField
    private double endLimitMagnitude;
    @DatabaseField
    private int endMood;
    @DatabaseField
    private String centerConstellation;
    @DatabaseField
    private String centerStar;
    @DatabaseField
    private double direction;
    @DatabaseField(canBeNull = true, foreign = true)
    private Person person;
    @DatabaseField(canBeNull = true, foreign = true)
    private Interval interval;

    @Override
    public String toString() {
        return "StateInterv{" +
                "id=" + id +
                ", startLimitMagnitude=" + startLimitMagnitude +
                ", startMood=" + startMood +
                ", endLimitMagnitude=" + endLimitMagnitude +
                ", endMood=" + endMood +
                ", centerConstellation='" + centerConstellation + '\'' +
                ", centerStar='" + centerStar + '\'' +
                ", direction=" + direction +
                ", person=" + person +
                ", interval=" + interval +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStartLimitMagnitude() {
        return startLimitMagnitude;
    }

    public void setStartLimitMagnitude(double startLimitMagnitude) {
        this.startLimitMagnitude = startLimitMagnitude;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public int getStartMood() {
        return startMood;
    }

    public void setStartMood(int startMood) {
        this.startMood = startMood;
    }

    public double getEndLimitMagnitude() {
        return endLimitMagnitude;
    }

    public void setEndLimitMagnitude(double endLimitMagnitude) {
        this.endLimitMagnitude = endLimitMagnitude;
    }

    public int getEndMood() {
        return endMood;
    }

    public void setEndMood(int endMood) {
        this.endMood = endMood;
    }

    public String getCenterConstellation() {
        return centerConstellation;
    }

    public void setCenterConstellation(String centerConstellation) {
        this.centerConstellation = centerConstellation;
    }


    public String getCenterStar() {
        return centerStar;
    }

    public void setCenterStar(String centerStar) {
        this.centerStar = centerStar;
    }

}
