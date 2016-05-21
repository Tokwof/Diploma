package marchenko.com.diplomameteors.dao;

import java.util.List;

import marchenko.com.diplomameteors.transfer_objects.transfer_objects_general.Meteor;

public interface MeteorDao {

    boolean insertListMeteors(Meteor[] meteorsArray);

    boolean clearTableMeteors();

    List<Meteor> findAllMeteorsOfInterval();

    List<Meteor> findAllMeteors();

    Meteor findMeteor(int meteorId);
}
