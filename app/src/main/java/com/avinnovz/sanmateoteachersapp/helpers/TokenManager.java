package com.avinnovz.sanmateoteachersapp.helpers;

import com.avinnovz.sanmateoteachersapp.models.response.Token;

import javax.inject.Inject;

import lombok.Data;

/**
 * Created by jayan on 4/16/2017.
 */

@Data
public class TokenManager {

    Token token;

    @Inject
    public TokenManager() {
    }
}
