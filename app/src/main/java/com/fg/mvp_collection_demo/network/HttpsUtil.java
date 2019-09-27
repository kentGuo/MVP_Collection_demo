package com.fg.mvp_collection_demo.network;

import com.fg.mvp_collection_demo.application.MyApplication;
import com.fg.mvp_collection_demo.util.Logger;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * 获取SSL的Socket
 */
public class HttpsUtil {
    private static final String CLIENT_TRUST_PASSWORD = "123456";//信任证书密码，该证书默认密码为changeit
    private static final String CLIENT_ARGEEMENT = "TLS";//使用协议
    private static final String CLIENT_TRUST_MANAGER = "X509";
    private static final String CLIENT_TRUST_KEYSTORE = "BKS";
    SSLContext sslContext = null;
    int resourseId = 0;

    public SSLContext getSslSocket() {
        try {
            //取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(CLIENT_ARGEEMENT);
            //取得TrustManagerFactory的X509秘钥管理器实例
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);
            //取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            InputStream is = MyApplication.getContext().getResources().openRawResource(resourseId);
            try {
                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
            //初始化秘钥管理器trustManager.init(tks);
            //初始化SSLContext
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (Exception e) {
            Logger.e("SslContextFactory", e.getMessage());
        }
        return sslContext;
    }
}