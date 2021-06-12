package com.gpetuhov.android.videotestapp.data.source.remote.utils

import android.annotation.SuppressLint
import javax.net.ssl.X509TrustManager

class UnsafeX509TrustManager : X509TrustManager {

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(
        p0: Array<out java.security.cert.X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(
        p0: Array<out java.security.cert.X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
        return arrayOf()
    }
}