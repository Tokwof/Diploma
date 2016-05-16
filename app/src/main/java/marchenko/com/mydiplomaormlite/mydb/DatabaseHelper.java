package marchenko.com.mydiplomaormlite.mydb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

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
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes. */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper implements DAOFactory{
    // name of the database file for your application
    private static final String DATABASE_NAME = "meteors.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the Day table
    // Integer - тип id в таблице
    private Dao<Day, Integer> dayDao = null;
    private Dao<EquatorialСoordinate, Integer> equatorialСoordinateDao = null;
    private Dao<Expedition, Integer> expeditionDao = null;
    private Dao<GroupOfResearcher, Integer> groupOfResearcherDao = null;
    private Dao<GroupPerson, Integer> groupPersonDao = null;
    private Dao<Interval, Integer> intervalDao = null;
    private Dao<Magnitude, Integer> magnitudeDao = null;
    private Dao<Meteor, Integer> meteorDao = null;
    private Dao<Person, Integer> personDao = null;
    private Dao<StateInterv, Integer> stateIntervDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data. */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            //myConnectionSource = connectionSource;
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Day.class);
            TableUtils.createTable(connectionSource, EquatorialСoordinate.class);
            TableUtils.createTable(connectionSource, Expedition.class);
            TableUtils.createTable(connectionSource, GroupOfResearcher.class);
            TableUtils.createTable(connectionSource, GroupPerson.class);
            TableUtils.createTable(connectionSource, Interval.class);
            TableUtils.createTable(connectionSource, Magnitude.class);
            TableUtils.createTable(connectionSource, Meteor.class);
            TableUtils.createTable(connectionSource, Person.class);
            TableUtils.createTable(connectionSource, StateInterv.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /** * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number. */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Day.class, true);
            TableUtils.dropTable(connectionSource, EquatorialСoordinate.class, true);
            TableUtils.dropTable(connectionSource, Expedition.class, true);
            TableUtils.dropTable(connectionSource, GroupOfResearcher.class, true);
            TableUtils.dropTable(connectionSource, GroupPerson.class, true);
            TableUtils.dropTable(connectionSource, Interval.class, true);
            TableUtils.dropTable(connectionSource, Magnitude.class, true);
            TableUtils.dropTable(connectionSource, Meteor.class, true);
            TableUtils.dropTable(connectionSource, Person.class, true);
            TableUtils.dropTable(connectionSource, StateInterv.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
         } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return super.getConnectionSource();
    }

    /**
     * Returns the Database Access Object (DAO) for our Day class. It will create it or just give the cached
     * value.
     */
    public Dao<Day, Integer> getDayDao()  {
        if (dayDao == null) {
            try {
                dayDao = getDao(Day.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dayDao;
    }

    @Override
    public Dao<EquatorialСoordinate, Integer> getEquatorialСoordinateDao() {
        if (equatorialСoordinateDao == null) {
            try {
                equatorialСoordinateDao = getDao(EquatorialСoordinate.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return equatorialСoordinateDao;
    }

    @Override
    public Dao<Expedition, Integer> getExpeditionDao() {
        if (expeditionDao == null) {
            try {
                expeditionDao = getDao(Expedition.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expeditionDao;
    }

    @Override
    public Dao<GroupOfResearcher, Integer> getGroupOfResearcherDao() {
        if (groupOfResearcherDao == null) {
            try {
                groupOfResearcherDao = getDao(GroupOfResearcher.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groupOfResearcherDao;
    }

    @Override
    public Dao<GroupPerson, Integer> getGroupPersonDao() {
        if (groupPersonDao == null) {
            try {
                groupPersonDao = getDao(GroupPerson.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groupPersonDao;
    }

    @Override
    public Dao<Interval, Integer> getIntervalDao() {
        if (intervalDao == null) {
            try {
                intervalDao = getDao(Interval.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return intervalDao;
    }

    @Override
    public Dao<Magnitude, Integer> getMagnitudeDao() {
        if (magnitudeDao == null) {
            try {
                magnitudeDao = getDao(Magnitude.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return magnitudeDao;
    }

    @Override
    public Dao<Meteor, Integer> getMeteorDao() {
        if (meteorDao == null) {
            try {
                meteorDao = getDao(Meteor.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return meteorDao;
    }

    @Override
    public Dao<Person, Integer> getPersonDao() {
        if (personDao == null) {
            try {
                personDao = getDao(Person.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personDao;
    }

    @Override
    public Dao<StateInterv, Integer> getStateIntervDao() {
        if (stateIntervDao == null) {
            try {
                stateIntervDao = getDao(StateInterv.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stateIntervDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override public void close() {
        super.close();
        dayDao = null;
        equatorialСoordinateDao = null;
        expeditionDao = null;
        groupOfResearcherDao = null;
        groupPersonDao = null;
        intervalDao = null;
        magnitudeDao = null;
        meteorDao = null;
        personDao = null;
        stateIntervDao = null;
    }


}
