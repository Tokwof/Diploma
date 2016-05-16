package marchenko.com.mydiplomaormlite.dao;

import java.util.List;

import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.EquatorialСoordinate;

public interface EqСoordDao {

    int insertEqСoord (EquatorialСoordinate equatorialСoordinate);

    List<EquatorialСoordinate> getAllEqCoord();
}
