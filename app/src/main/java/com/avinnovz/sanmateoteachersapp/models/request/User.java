package com.avinnovz.sanmateoteachersapp.models.request;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by jayan on 4/16/2017.
 */

@Data
@RequiredArgsConstructor
public class User {
    @NonNull String username;
    @NonNull String password;
}
