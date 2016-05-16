package marchenko.com.mydiplomaormlite.dao;

import java.util.Collection;

import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Interval;
import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Person;

/**
 * Created by Sycamore on 09.05.2016.
 */
public interface IntervalsDao {
    /**
     * Возвращает номер созданного дня
     * @return 0 в случае ошибки
     */
    int insertInterval(Interval interval);

    Interval findInterval();

    boolean updateInterval(Interval interval);
    /**
     * @return true если обновлено успешно
     */
}
