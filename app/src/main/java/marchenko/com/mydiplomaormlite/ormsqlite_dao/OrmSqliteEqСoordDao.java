package marchenko.com.mydiplomaormlite.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import marchenko.com.mydiplomaormlite.dao.EqСoordDao;
import marchenko.com.mydiplomaormlite.mydb.DatabaseManager;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.EquatorialСoordinate;

public class OrmSqliteEqСoordDao implements EqСoordDao {
    Dao<EquatorialСoordinate, Integer> eqCoordDao = DatabaseManager.getInstance().getHelper().getEquatorialСoordinateDao();
    @Override
    public int insertEqСoord(EquatorialСoordinate equatorialСoordinate) {
        try {
            eqCoordDao.create(equatorialСoordinate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<EquatorialСoordinate> getAllEqCoord() {
        try {
            return eqCoordDao.queryForAll();
        } catch (SQLException e) {
            return null;
        }

    }
}
