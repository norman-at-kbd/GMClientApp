package com.romazzz.gmclient.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by z01tan on 5/16/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerAppScope {
}
