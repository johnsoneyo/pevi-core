/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.repository;

import com.pevi.core.models.entity.PvAdmin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface AuthRepository extends CrudRepository<PvAdmin, Integer> {

    @Query(value = "select * from pv_admin where login_id = ?1 and password = ?2", nativeQuery = true)
    PvAdmin login(String loginId, String password);

}
