package com.romazzz.gmclient.ui;

/**
 * Created by z01tan on 5/16/17.
 */

public interface IMVPPresenter <T extends IMVPView> {
    void onAttach(T view);
    void onDetach();
}
