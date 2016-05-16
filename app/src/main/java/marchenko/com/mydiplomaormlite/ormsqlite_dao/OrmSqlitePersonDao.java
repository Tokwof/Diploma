package marchenko.com.mydiplomaormlite.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Collection;

import marchenko.com.mydiplomaormlite.dao.PersonDao;
import marchenko.com.mydiplomaormlite.mydb.DatabaseManager;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Person;

/**
 * c26
 */
public class OrmSqlitePersonDao implements PersonDao {
    Dao<Person, Integer> personDao = DatabaseManager.getInstance().getHelper().getPersonDao();

    @Override
    public int insertPerson(Person person) {
        try {
            return personDao.create(person);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void insertListPersons(Person[] person) {
        try {
            for(int i=0; i<person.length; i++) {
                personDao.create(person[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deletePerson(Person person) {
        try {
            if(personDao.delete(person) != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePerson(int id) {
        try {
            if(personDao.deleteById(id) != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Person findPerson(int id) {
        try {
            return personDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean updatePerson(Person person) {
        try {
            return personDao.update(person) == 0 ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePerson(Person person, int id) {
        try {
            return personDao.updateId(person, id) == 0 ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection selectPersonByCriteria() {
        return null;
    }
}
