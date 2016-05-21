package marchenko.com.diplomameteors.dao;

import java.util.List;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.EquatorialСoordinate;

public interface EqСoordDao {

    int insertEqСoord (EquatorialСoordinate equatorialСoordinate);

    List<EquatorialСoordinate> getAllEqCoord();
}
