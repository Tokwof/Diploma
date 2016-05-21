package marchenko.com.diplomameteors.dao;

import java.util.Date;
import java.util.List;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Day;
import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Expedition;

public interface DayDao {
    boolean createDay(Day day);
    /**
     * Возвращает номер созданного дня. Устанавливается текущее время
     * @return 0 в случае ошибки
     */
    int insertDay(Expedition expedition);
    /**
     * Возвращает номер созданного дня. Устанавливается заданное время
     * @return 0 в случае ошибки
     */
    int insertDay(Expedition expedition, Date date);
    /**
     * @return true если удалено успешно
     */
    boolean deleteDay(Day day);
    boolean deleteAllDays(int expeditionId);
    /**
     * @return null, если не найден
     */
    Day findDay(int id);
    Day findFirstDayOfExpedition(Expedition expedition);
    /**
     * @return true если обновлено успешно
     */
    boolean updateDay(Day newDay);
    boolean createIfNotExistInThatExpedition(Day day);
    List<Day> findAllDaysOfExpedition(int expeditionId);
}
