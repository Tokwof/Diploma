package marchenko.com.diplomameteors.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

import marchenko.com.diplomameteors.dao.DayDao;
import marchenko.com.diplomameteors.mydb.DatabaseManager;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Day;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Expedition;

import java.util.Date;
import java.util.List;

public class OrmSqliteDayDao implements DayDao {
    Dao<Day, Integer> dayDao = DatabaseManager.getInstance().getHelper().getDayDao();
    @Override
    public boolean createDay(Day day) {
        try {
            if(dayDao.create(day) != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int insertDay(Expedition expedition) {
        Day day = new Day();
        try {
            Date currentTime = new Date();
            day.setDateTime(currentTime);
            day.setExpedition(expedition);
            return dayDao.create(day);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int insertDay(Expedition expedition, Date date) {
        Day day = new Day();
        try {
            day.setDateTime(date);
            day.setExpedition(expedition);
            return dayDao.create(day);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean deleteDay(Day day) {
        try {
            if(dayDao.delete(day) != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteAllDays(int expeditionId) {
        try {
            DeleteBuilder<Day, Integer> deleteBuilder = dayDao.deleteBuilder();
            deleteBuilder.where().eq("expedition_id", expeditionId);
            if(deleteBuilder.delete() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Day findDay(int id) {
        Day day;
        try {
            day = dayDao.queryForId(id);
            return day;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Day findFirstDayOfExpedition(Expedition expedition) {
        try {
            QueryBuilder<Day, Integer> builder = dayDao.queryBuilder();
            builder.where().eq("expedition_id", expedition.getId());
            builder.orderBy("datetime",false);
            return dayDao.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Day> findAllDaysOfExpedition(int expeditionId) {
        try {
            QueryBuilder<Day, Integer> builder = dayDao.queryBuilder();
            builder.where().eq("expedition_id", expeditionId);
            builder.orderBy("datetime",false);
            return dayDao.query(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateDay(Day newDay) {
        try {
            return dayDao.update(newDay) == 0 ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createIfNotExistInThatExpedition(Day day) {
        boolean isCreate = false;
        QueryBuilder<Day, Integer> builder = dayDao.queryBuilder();
        try {
            builder.where().eq("datetime", day.getDateTime()).and().eq("expedition_id", day.getExpedition().getId());
            if(dayDao.query(builder.prepare()).isEmpty()) {
                isCreate = createDay(day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreate;

    }
}
