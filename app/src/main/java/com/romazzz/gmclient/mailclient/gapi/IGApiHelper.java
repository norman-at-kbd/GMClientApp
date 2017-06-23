package com.romazzz.gmclient.mailclient.gapi;

import android.content.Intent;

/**
 * Created by z01tan on 6/23/17.
 */

public interface IGApiHelper extends IGApiAvalability, IPermissions, ICredentialsProvider {

    /**
     *
     * @param callback callback on permissions getting succes
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     * @param data
     */

    void onPermissionRequestResult(OnPermissionRequestSuccess callback,
                                   int requestCode,
                                   int resultCode,
                                   Intent data);
    interface OnPermissionRequestSuccess {
        void onSuccess();
    }
}
