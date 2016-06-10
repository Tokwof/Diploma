package marchenko.com.diplomameteors.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "Meteors")
public class Meteor {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int number;
    @DatabaseField
    private Date time;
    @DatabaseField
    private double length;
    @DatabaseField
    private double duration;
    @DatabaseField
    private int speed;
    @DatabaseField
    private int color;
    @DatabaseField
    private int contour;
    @DatabaseField
    private int zenit;
    /**
     * P - направление полёта метеора, определяется с помощью круга, который делится
     * на 12 частей часов, а часы отсчитываются от севера (0ч) через восток - 3 ч.
     * Метеор мысленно переносится в зенит параллельно себе
     */
    @DatabaseField
    private double p;
    /**
     * P' - зенитное направление полета, отсчитывается от зенита через середину метеора.
     * восток - 3ч, юг - 6ч, запад - 9ч. Метеор, летящий в зените, зенитного направления не имеет
     */
    @DatabaseField
    private double pprime;
    @DatabaseField
    private String notes;
    @DatabaseField
    private double traceLength;
    @DatabaseField
    private double traceDuration;
    @DatabaseField
    private double traceContour;
    @DatabaseField
    private String source;
    @DatabaseField(canBeNull = true, foreign = true)
    private Interval interval;

    @Override
    public String toString() {
        return "Meteor{" +
                "id=" + id +
                ", number=" + number +
                ", time=" + time +
                ", length=" + length +
                ", duration=" + duration +
                ", speed=" + speed +
                ", color=" + color +
                ", contour=" + contour +
                ", zenit=" + zenit +
                ", p=" + p +
                ", pprime=" + pprime +
                ", notes='" + notes + '\'' +
                ", traceLength=" + traceLength +
                ", traceDuration=" + traceDuration +
                ", traceContour=" + traceContour +
                ", source='" + source + '\'' +
                ", interval=" + interval +
                '}';
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public boolean setLength(double length) {
        if(length >= 0 && length <=180) {
            this.length = length;
            return true;
        }
        return false;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getContour() {
        return contour;
    }

    public void setContour(int contour) {
        this.contour = contour;
    }

    public int getZenit() {
        return zenit;
    }

    public double getP() {
        return p;
    }

    public double getPprime() {
        return pprime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTraceLength() {
        return traceLength;
    }

    public void setTraceLength(double traceLength) {
        this.traceLength = traceLength;
    }

    public double getTraceDuration() {
        return traceDuration;
    }

    public void setTraceDuration(double traceDuration) {
        this.traceDuration = traceDuration;
    }

    public double getTraceContour() {
        return traceContour;
    }

    public void setTraceContour(double traceContour) {
        this.traceContour = traceContour;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean setZenit(int zenit) {
        final int min = 0;
        final int max = 90;
        if (zenit >= min && zenit <= max) {
            this.zenit = zenit;
            return true;
        }
        return false;
    }

    /**
     * Принимаем и оставляемв часах. В градусы не нужно переводить
     *
     * @param p
     * @return
     */
    public boolean setP(double p) {
        final double min = 0;
        final double max = 12;
        if (p >= min && p < max) {
            // p = 360 - p*30;
            this.p = p;
            return true;
        }
        return false;
    }

    /**
     * Сохраняет полученоой dashedP в часах в градусном эквиваленте.
     *
     * @param pprime направления в часах [0..12)
     * @return установлено ли значение. да - если введено в верном диапазоне
     */
    public boolean setPprime(double pprime) {
        final double min = 0;
        final double max = 12;
        if (pprime >= min && pprime < max) {
            this.pprime = pprime * 15;
            return true;
        }
        return false;
    }
}
