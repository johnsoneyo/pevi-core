/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.constants.PeviException;
import com.pevi.core.models.entity.PvAdmin;
import com.pevi.core.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class AuthService {

    @Autowired
    AuthRepository authRep;

    public PvAdmin login(String loginId, String password) throws PeviException {
        PvAdmin login = authRep.login(loginId, password);
        if (login == null) {
            throw new PeviException("Pv admin doesnt exist");
        } else {
            return login;
        }

    }

}
