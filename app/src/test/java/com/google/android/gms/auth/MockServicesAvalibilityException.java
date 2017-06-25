package com.google.android.gms.auth;

import android.content.Intent;

/**
 * Created by z01tan on 6/25/17.
 */

public class MockServicesAvalibilityException extends GooglePlayServicesAvailabilityException {
    public MockServicesAvalibilityException(int var1, String var2, Intent var3) {
        super(var1,var2, var3);
    }
}