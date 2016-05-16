package marchenko.com.mydiplomaormlite.dao;

import java.util.Collection;

import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Person;

/**
 * Created by Sycamore on 09.05.2016.
 */
public interface PersonDao {
    /**
     * Возвращает номер созданного дня
     * @return 0 в случае ошибки
     */
    int insertPerson(Person person);
    void insertListPersons(Person[] person);
    /**
     * @return true если удалено успешно
     */
    boolean deletePerson(Person person);

    boolean deletePerson(int id);
    /**
     * @return null, если не найден
     */
    Person findPerson(int id);

    boolean updatePerson(Person person, int id);
    /**
     * @return true если обновлено успешно
     */
    boolean updatePerson(Person person);
    Collection selectPersonByCriteria();
}
