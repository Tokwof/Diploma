package marchenko.com.diplomameteors.ormsqlite_dao;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import marchenko.com.diplomameteors.dao.EqСoordDao;
import marchenko.com.diplomameteors.mydb.DatabaseManager;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.EquatorialСoordinate;

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
