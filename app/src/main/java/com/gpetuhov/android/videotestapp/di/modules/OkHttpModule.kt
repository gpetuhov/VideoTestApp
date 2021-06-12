package com.gpetuhov.android.videotestapp.di.modules

import com.gpetuhov.android.videotestapp.data.source.local.AppConstants
import com.gpetuhov.android.videotestapp.data.source.remote.utils.UnsafeX509TrustManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyStore
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*

@Module
class OkHttpModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return getUnsafeOkHttpClient(loggingInterceptor)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    // Create OkHttpClient, that ignores expired SSL certificate of the host
    // Code taken from:
    // https://stackoverflow.com/questions/37686625/disable-ssl-certificate-check-in-retrofit-library/46969809#46969809
    private fun getUnsafeOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(UnsafeX509TrustManager())

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val trustManagerFactory: TrustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            val trustManagers: Array<TrustManager> =
                trustManagerFactory.trustManagers
            check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                "Unexpected default trust managers:" + trustManagers.contentToString()
            }

            val trustManager = trustManagers[0] as X509TrustManager

            return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier { _, _ -> true }
                .addNetworkInterceptor(loggingInterceptor)
                .connectTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build()

        } catch (e: Exception) {
            return getSafeOkHttpClient(loggingInterceptor)
        }
    }

    private fun getSafeOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}