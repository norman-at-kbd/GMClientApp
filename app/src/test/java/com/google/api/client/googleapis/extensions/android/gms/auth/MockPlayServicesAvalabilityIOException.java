package com.google.api.client.googleapis.extensions.android.gms.auth;

import com.google.android.gms.auth.MockServicesAvalibilityException;

/**
 * Created by z01tan on 6/25/17.
 */

public class MockPlayServicesAvalabilityIOException extends GooglePlayServicesAvailabilityIOException {
    public MockPlayServicesAvalabilityIOException(MockServicesAvalibilityException exception) {
        super(exception);
    }
}
