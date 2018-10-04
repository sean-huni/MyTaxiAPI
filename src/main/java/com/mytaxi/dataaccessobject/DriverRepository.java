package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends JpaRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    List<DriverDO> findAllByUsernameLikeOrFirstnameLikeOrSurnameLikeAndOnlineStatusLike(String username,
                                                                                        String firstname, String surname, OnlineStatus onlineStatus);
}
