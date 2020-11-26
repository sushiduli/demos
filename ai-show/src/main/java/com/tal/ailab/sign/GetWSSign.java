package com.tal.ailab.sign;

import com.tal.ailab.enums.RequestMethod;
import java.util.Date;
import java.util.Map;
import static com.tal.ailab.util.HttpUtil.APPLICATION_JSON;

public class GetWSSign {

    /**
     *
     * 获取WS签名鉴权
     * @param access_key_id
     * @param access_key_secret
     * @param timestamp
     * @param urlParams
     * @return
     * @throws Exception
     */
    public static String getSign (
            String url,
            String access_key_id,
            String access_key_secret,
            Date timestamp,
            Map<String, Object> urlParams
    ) throws Exception{

        SignatureSDK.getSignature(
                access_key_id,
                access_key_secret,
                timestamp,
                urlParams,
                null,
                RequestMethod.GET,
                APPLICATION_JSON);
        //签名鉴权参数放在URL中
        return url + "?" + SignatureSDK.urlParamsFormat(urlParams);
    }

}
