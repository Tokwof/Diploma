package marchenko.com.mydiplomaormlite.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import marchenko.com.mydiplomaormlite.dao.IntervalsDao;
import marchenko.com.mydiplomaormlite.mydb.DatabaseManager;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Interval;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Person;

public class OrmSqliteIntervalDao implements IntervalsDao {
    Dao<Interval, Integer> intervalDao = DatabaseManager.getInstance().getHelper().getIntervalDao();
    @Override
    public int insertInterval(Interval interval) {
        try {
            intervalDao.create(interval);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Interval findInterval() {
        return null;
    }

    @Override
    public boolean updateInterval(Interval interval) {
        return false;
    }
}
