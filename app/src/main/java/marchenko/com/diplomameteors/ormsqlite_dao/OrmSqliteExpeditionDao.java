package marchenko.com.diplomameteors.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import marchenko.com.diplomameteors.dao.ExpeditionDao;
import marchenko.com.diplomameteors.mydb.DatabaseManager;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Day;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Expedition;

public class OrmSqliteExpeditionDao implements ExpeditionDao {
    Dao<Expedition, Integer> expeditionDao = DatabaseManager.getInstance().getHelper().getExpeditionDao();
    final DateFormat df = new SimpleDateFormat(Day.DATE_FORMAT);
    OrmSqliteDayDao dayDao = new OrmSqliteDayDao();

    @Override
    public boolean createExpedition(Expedition expedition) {
        try {
            if(expeditionDao.create(expedition) != 0) {
                Day day = new Day();
                day.setExpedition(expedition);
                day.setDateTime(df.parse(expedition.getFirstDay()));
                dayDao.createDay(day);
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteExpedition(Expedition expedition) {
        try {
            if(expeditionDao.delete(expedition) != 0) {
                dayDao.deleteAllDays(expedition.getId());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteExpedition(int expeditionId) {
        try {
            if(expeditionDao.deleteById(expeditionId) != 0) {
                dayDao.deleteAllDays(expeditionId);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Expedition findExpedition(int id) {
        try {
            return expeditionDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public boolean updateExpedition(Expedition expedition) {
//
//        try {
//            Day newDay = new Day();
//            newDay.setExpedition(expedition);
//            try {
//                newDay.setDateTime(df.parse(expedition.getFirstDay()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            dayDao.updateDay(newDay);
//            return expeditionDao.update(expedition) == 0 ? false : true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    @Override
    public boolean updateExpedition(Expedition expedition, int oldDayId) {

        try {
            Day newDay = new Day();
            newDay.setId(oldDayId);
            newDay.setExpedition(expedition);
            try {
                newDay.setDateTime(df.parse(expedition.getFirstDay()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dayDao.updateDay(newDay);
            return expeditionDao.update(expedition) == 0 ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    @Override
//    public boolean updateExpedition(Expedition expedition, int id) {
//        try {
//            return expeditionDao.updateId(expedition, id) == 0 ? false : true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    @Override
    public List<Expedition> selectExpeditionOrderedByName() {
        Dao<Expedition, Integer> expeditionDao = DatabaseManager.getInstance().getHelper().getExpeditionDao();
        QueryBuilder<Expedition, Integer> builder = expeditionDao.queryBuilder();
        builder.orderBy(Expedition.NAME_FIELD_NAME, false);//.limit(30L);
        try {
            return expeditionDao.query(builder.prepare());
        } catch (SQLException e) {
            return null;
        }
    }
}
