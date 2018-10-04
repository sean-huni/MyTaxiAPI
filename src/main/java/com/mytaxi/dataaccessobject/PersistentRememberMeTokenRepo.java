package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.PersistentRememberMeTokenDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.dataaccessobject
 * USER      : sean
 * DATE      : 04-Thu-Oct-2018
 * TIME      : 00:26
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface PersistentRememberMeTokenRepo extends JpaRepository<PersistentRememberMeTokenDO, Long> {
    Optional<PersistentRememberMeTokenDO> findBySeries(String series);

    Optional<PersistentRememberMeTokenDO> findByUsername(String username);
}
