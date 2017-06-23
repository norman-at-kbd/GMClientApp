package com.romazzz.gmclient.mailclient.gapi;

import android.support.annotation.NonNull;

/**
 * Created by z01tan on 6/15/17.
 */

public interface IPermissions {
    void chooseAccount(@NonNull onAccountChosen accc, @NonNull pickUpAccount pickUpAccount);
    void requestPermissions();

    interface onAccountChosen {
        void onAccChosen();
    }

    interface pickUpAccount {
        void pickUpAccount();
    }
}
