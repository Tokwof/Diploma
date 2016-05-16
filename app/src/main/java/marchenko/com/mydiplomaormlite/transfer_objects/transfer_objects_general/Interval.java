package marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "Intervals")
public class Interval {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int number;
    @DatabaseField
    private Date timeBegin;
    @DatabaseField
    private Date timeEnd;
    @DatabaseField
    private double clouds;
    @DatabaseField
    private double moon;
    @DatabaseField
    private int moonHeight;
    @DatabaseField
    private int radiantHeight;
    @DatabaseField(canBeNull = true, foreign = true)
    private Day day;
    @DatabaseField(canBeNull = true, foreign = true)
    private GroupOfResearcher group;

    @Override
    public String toString() {
        return "Interval{" +
                "id=" + id +
                ", number=" + number +
                ", timeBegin=" + timeBegin +
                ", timeEnd=" + timeEnd +
                ", clouds=" + clouds +
                ", moon=" + moon +
                ", moonHeight=" + moonHeight +
                ", radiantHeight=" + radiantHeight +
                ", day=" + day +
                ", group=" + group +
                '}';
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public GroupOfResearcher getGroup() {
        return group;
    }

    public void setGroup(GroupOfResearcher group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(java.util.Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public double getMoon() {
        return moon;
    }

    public void setMoon(double moon) {
        this.moon = moon;
    }

    public int getMoonHeight() {
        return moonHeight;
    }

    public void setMoonHeight(int moonHeight) {
        this.moonHeight = moonHeight;
    }

    public int getRadiantHeight() {
        return radiantHeight;
    }

    public void setRadiantHeight(int radiantHeight) {
        this.radiantHeight = radiantHeight;
    }
}
