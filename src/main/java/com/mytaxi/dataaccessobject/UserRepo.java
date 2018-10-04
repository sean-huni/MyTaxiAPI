package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.userdetail
 * USER      : sean
 * DATE      : 03-Wed-Oct-2018
 * TIME      : 23:31
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface UserRepo extends JpaRepository<UserDO, Long> {
    UserDO findByUsername(String username);
}
