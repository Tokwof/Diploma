package marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Magnitudes")
public class Magnitude {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private double magnitudeValue;
    @DatabaseField(canBeNull = true, foreign = true)
    private Person person;
    @DatabaseField(canBeNull = true, foreign = true)
    private Meteor meteor;

    @Override
    public String toString() {
        return "Magnitude{" +
                "id=" + id +
                ", magnitudeValue=" + magnitudeValue +
                ", person=" + person +
                ", meteor=" + meteor +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Meteor getMeteor() {
        return meteor;
    }

    public void setMeteor(Meteor meteor) {
        this.meteor = meteor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMagnitudeValue() {
        return magnitudeValue;
    }

    public void setMagnitudeValue(double magnitudeValue) {
        this.magnitudeValue = magnitudeValue;
    }
}
