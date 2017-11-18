/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.controllers;

import com.pevi.core.constants.PeviException;
import com.pevi.core.models.entity.PvAdmin;
import static com.pevi.core.models.entity.PvAdmin_.loginId;
import static com.pevi.core.models.entity.PvAdmin_.password;
import com.pevi.core.services.AuthService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService auth;

    @PostMapping("pvlogin")
    public ResponseEntity login(@RequestBody PvAdmin pva) {
        try {
            PvAdmin login = auth.login(pva.getLoginId(), pva.getPassword());
            return new ResponseEntity<PvAdmin>(login, HttpStatus.OK);
        } catch (PeviException ex) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
