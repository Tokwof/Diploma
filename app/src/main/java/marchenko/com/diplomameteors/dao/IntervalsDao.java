package marchenko.com.diplomameteors.dao;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Interval;

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
