package com.avinnovz.sanmateoteachersapp.data.api;

import com.avinnovz.sanmateoteachersapp.models.request.User;
import com.avinnovz.sanmateoteachersapp.models.response.Token;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jayan on 4/13/2017.
 */

public interface ApiInterface {

    @POST("api/user/auth")
    Observable<Token> login(@Body User user);
}
