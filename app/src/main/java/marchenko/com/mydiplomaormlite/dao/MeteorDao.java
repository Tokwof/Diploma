package marchenko.com.mydiplomaormlite.dao;

import java.util.List;

import marchenko.com.mydiplomaormlite.transfer_objects.transfer_objects_general.Meteor;

/**
 * Created by Sycamore on 15.05.2016.
 */
public interface MeteorDao {

    boolean insertListMeteors(Meteor[] meteorsArray);

    boolean clearTableMeteors();

    List<Meteor> findAllMeteorsOfInterval();

    public List<Meteor> findAllMeteors();

    public Meteor findMeteor(int meteorId);
}
