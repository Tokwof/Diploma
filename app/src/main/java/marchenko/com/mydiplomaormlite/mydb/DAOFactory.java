package marchenko.com.mydiplomaormlite.mydb;

import com.j256.ormlite.dao.Dao;

import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Day;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.EquatorialСoordinate;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Expedition;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.GroupOfResearcher;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.GroupPerson;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Interval;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Magnitude;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Meteor;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Person;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.StateInterv;

/**
 * Created by Sycamore on 09.05.2016.
 */
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
