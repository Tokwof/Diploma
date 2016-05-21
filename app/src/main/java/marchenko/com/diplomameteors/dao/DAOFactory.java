package marchenko.com.diplomameteors.dao;

import com.j256.ormlite.dao.Dao;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Day;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.EquatorialСoordinate;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Expedition;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.GroupOfResearcher;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.GroupPerson;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Interval;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Magnitude;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Meteor;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Person;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.StateInterv;

public interface DAOFactory {
    Dao<Day, Integer> getDayDao();
    Dao<EquatorialСoordinate, Integer> getEquatorialСoordinateDao();
    Dao<Expedition, Integer> getExpeditionDao();
    Dao<GroupOfResearcher, Integer> getGroupOfResearcherDao();
    Dao<GroupPerson, Integer> getGroupPersonDao();
    Dao<Interval, Integer> getIntervalDao();
    Dao<Magnitude, Integer> getMagnitudeDao();
    Dao<Meteor, Integer> getMeteorDao();
    Dao<Person, Integer> getPersonDao();
    Dao<StateInterv, Integer> getStateIntervDao();
}
