package com.romazzz.gmclient.mailclient.gapi;

import android.support.annotation.NonNull;

/**
 * Created by z01tan on 6/15/17.
 */

public interface IPermissions {
    /**
     * @param accc callback which called if account is set
     * @param pick callback which has to be called to choose account
     * @param obj Activity or Fragment object needed for easypermissions
     */
    void chooseAccount(@NonNull onAccountChosen accc,
                       @NonNull pickUpAccount pick,
                       @NonNull Object obj);

    interface onAccountChosen {
        void onAccChosen();
    }

    interface pickUpAccount {
        void pickUpAccount();
    }
}
