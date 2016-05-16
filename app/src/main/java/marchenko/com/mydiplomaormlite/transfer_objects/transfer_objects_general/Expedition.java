package marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Transfer Object
 */
@DatabaseTable(tableName = "Expeditions")
public class Expedition implements Serializable {
    private static final long serialVersionUID = -6582623980712135028L;
    public static final String NAME_FIELD_NAME = "name";
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = NAME_FIELD_NAME)
    private String name;
    @DatabaseField
    private String mission;
    /**
     * Широта места наблюдения (градусы и минуты)
     * fi
     */
    @DatabaseField
    private double latitude;
    /**
     * вспомогательная переменная
     */
    private int latitudeObservations_fi_minutes;
    private int latitudeObservations_fi_seconds;
    private String firstDay;

    @Override
    public String toString() {
        return "Expedition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mission='" + mission + '\'' +
                ", latitude=" + latitude +
                ", latitudeObservations_fi_minutes=" + latitudeObservations_fi_minutes +
                ", latitudeObservations_fi_seconds=" + latitudeObservations_fi_seconds +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getLatitudeObservations_fi_minutes() {
        return latitudeObservations_fi_minutes;
    }

    public boolean setLatitude(double latitude) {
        final double min = -90;
        final double max = 90;
        if (latitude >= min && latitude <= max) {
            this.latitude = latitude;
            return true;
        }
        return false;
    }

    public boolean setLatitudeObservations_fi_minutes(int latitudeObservations_fi_minutes) {
        final int min = 0;
        final int max = 60;
        if (latitudeObservations_fi_minutes >= min && latitudeObservations_fi_minutes < max) {
            this.latitudeObservations_fi_minutes = latitudeObservations_fi_minutes;
            return true;
        }
        return false;
    }

    public int getLatitudeObservations_fi_seconds() {
        return latitudeObservations_fi_seconds;
    }

    public boolean setLatitudeObservations_fi_seconds(int latitudeObservations_fi_seconds) {
        final int min = 0;
        final int max = 60;
        if (latitudeObservations_fi_seconds >= min && latitudeObservations_fi_seconds < max) {
            this.latitudeObservations_fi_minutes = latitudeObservations_fi_minutes;
            return true;
        }
        return false;
    }

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

}
