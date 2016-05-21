package marchenko.com.diplomameteors.dao;

import java.util.List;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Expedition;

public interface ExpeditionDao {

    boolean createExpedition(Expedition expedition);

    /**
     * @return true если удалено успешно
     */
    boolean deleteExpedition(Expedition expedition);

    boolean deleteExpedition(int id);
    /**
     * @return null, если не найден
     */
    Expedition findExpedition(int id);

//    public boolean updateExpedition(Expedition expedition, int id);
    /**
     * @return true если обновлено успешно
     */
//    public boolean updateExpedition(Expedition expedition);

    boolean updateExpedition(Expedition expedition, int oldDayId);

    /**
     * Возвращает коллекцию данных, отсортированных по алфавиту по имени экспедиции
     * @return null в случае неудачи
     */
    List<Expedition> selectExpeditionOrderedByName();


}
