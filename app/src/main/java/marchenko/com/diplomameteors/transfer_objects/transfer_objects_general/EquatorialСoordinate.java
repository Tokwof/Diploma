package marchenko.com.diplomameteors.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Transfer Object
 */
@DatabaseTable(tableName = "EquatorialСoordinates")
public class EquatorialСoordinate {
    @DatabaseField(generatedId = true)
    private int id;
    /**
     * delta
     * cклонение (δ) – в градусах (-90..+90)
     */
    @DatabaseField
    private double declension;
    /**
     * Экваториальные координаты:
     * 2) часовой угол (t) - в часах (0h..24h)
     */

    @DatabaseField
    private double right_ascension;
    @DatabaseField
    private double hourAngleInHour;
    /**
     * Экваториальные координаты:
     * 2) часовой угол (t) - в градусах (0..360)
     */
    @DatabaseField
    private double hourAngleInDegree;

    public double getQuadrantNumber() {
        return quadrantNumber;
    }

    public void setQuadrantNumber(double quadrantNumber) {
        this.quadrantNumber = quadrantNumber;
    }

    @DatabaseField
    private double quadrantNumber;
    @DatabaseField(canBeNull = true, foreign = true)
    private Meteor meteor;

    @Override
    public String toString() {
        return "EquatorialСoordinate{" +
                "id=" + id +
                ", declension=" + declension +
                ", hourAngleInHour=" + hourAngleInHour +
                ", hourAngleInDegree=" + hourAngleInDegree +
                ", meteor=" + meteor +
                '}';
    }

    public Meteor getMeteor() {
        return meteor;
    }

    public void setMeteor(Meteor meteor) {
        this.meteor = meteor;
    }

    // ----------------------------------------- getter-s ------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDeclension() {
        return declension;
    }

    public double getHourAngleInHour() {
        return hourAngleInHour;
    }

    public double getHourAngleInDegree() {
        return hourAngleInDegree;
    }

    // ----------------------------------------- setter-s ------------------------------------------
    public boolean setDeclension(double declension) {
        final double min = -90;
        final double max = 90;
        if (declension >= min && declension <= max) {
            this.declension = declension;
            return true;
        }
        return false;
    }

    public boolean setHourAngleInHour(double hourAngleInHour) {
        final double min = 0;
        final double max = 24;
        if (hourAngleInHour >= min && hourAngleInHour < max) {
            this.hourAngleInHour = hourAngleInHour;
            return true;
        }
        return false;
    }

    public boolean setHourAngleInDegree(double hourAngleInDegree) {
        final double min = 0;
        final double max = 360;
        if (hourAngleInHour >= min && hourAngleInHour <= max) {
            this.hourAngleInDegree = hourAngleInDegree;
            return true;
        }
        return false;
    }
    public double getRight_ascension() {
        return right_ascension;
    }

    public void setRight_ascension(double right_ascension) {
        this.right_ascension = right_ascension;
    }

}
