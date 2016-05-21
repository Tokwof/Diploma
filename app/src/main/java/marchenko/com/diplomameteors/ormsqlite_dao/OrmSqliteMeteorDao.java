package marchenko.com.diplomameteors.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import marchenko.com.diplomameteors.dao.MeteorDao;
import marchenko.com.diplomameteors.mydb.DatabaseManager;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Meteor;

public class OrmSqliteMeteorDao implements MeteorDao {
    Dao<Meteor, Integer> meteorDao = DatabaseManager.getInstance().getHelper().getMeteorDao();
    @Override
    public boolean insertListMeteors(Meteor[] meteorsArray) {
        for(int i=0; i<meteorsArray.length; i++) {
            try {
                meteorDao.create(meteorsArray[i]);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean clearTableMeteors() {
            ConnectionSource con = DatabaseManager.getInstance().getHelper().getConnectionSource();
        try {
            TableUtils.clearTable(con, Meteor.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Meteor> findAllMeteorsOfInterval() {
      //  meteorDao.queryForAll();
        return null;
    }

    @Override
    public List<Meteor> findAllMeteors() {
        try {
            return meteorDao.queryForAll();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Meteor findMeteor(int meteorId) {
        try {
            return meteorDao.queryForId(meteorId);
        } catch (SQLException e) {
            return null;
        }
    }
}
