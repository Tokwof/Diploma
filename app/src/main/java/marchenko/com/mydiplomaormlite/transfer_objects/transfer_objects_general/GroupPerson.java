package marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Group_Person")
public class GroupPerson {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String role;
    @DatabaseField
    private String program;
    @DatabaseField(canBeNull = true, foreign = true)
    private GroupOfResearcher group;
    @DatabaseField(canBeNull = true, foreign = true)
    private Person person;

    @Override
    public String toString() {
        return "GroupPerson{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", program='" + program + '\'' +
                ", group=" + group +
                ", person=" + person +
                '}';
    }

    public GroupOfResearcher getGroup() {
        return group;
    }

    public void setGroup(GroupOfResearcher group) {
        this.group = group;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

}
