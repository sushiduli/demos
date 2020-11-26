package com.tal.ailab.util;

import com.tal.ailab.enums.RequestMethod;
import com.tal.ailab.sign.SendSignHttp;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.tal.ailab.util.HttpUtil.*;

/**
 * @author kong gang
 * @version v1
 * @className PostUtil
 * @packageName com.tal.ailab.util
 * @description xxx
 * @date 2020/11/25 15:08
 */
public class PostUtil {
    public static  String sendPostOrPatchOrPutJSON (String access_key_id, String access_key_secret, String url, Map<String, Object> urlParams, Map<String, Object> bodyParams, RequestMethod requestMethod) throws Exception{
        //设置请求头content-type
        String contentType = APPLICATION_JSON;

        //当前时间（东8区）
        Date timestamp = DateUtil.getCurrentDate();

        /**
         * 获取签名鉴权，并发送请求
         */
        HttpResponse httpResponse = SendSignHttp.sendRequest(
                access_key_id,
                access_key_secret,
                timestamp,
                url,
                urlParams,
                bodyParams,
                requestMethod,
                contentType);
        //响应结果httpResponse
        String resposeJson = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());
        System.out.println(resposeJson);
        return resposeJson;
    }
    public static  String sendPostOrPatchOrPutBinary (String access_key_id, String access_key_secret, String url, Map<String, Object> urlParams, File f, RequestMethod requestMethod) throws Exception{

        //根据接口要求，填写实际Body参数
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put(INPUT_STREAM_ENTITY, new FileInputStream(f));

        //设置请求头content-type
        String contentType = BINARY;

        //当前时间（东8区）
        Date timestamp = DateUtil.getCurrentDate();

        HttpResponse httpResponse = SendSignHttp.sendRequest(
                access_key_id,
                access_key_secret,
                timestamp,
                url,
                urlParams,
                bodyParams,
                requestMethod,
                contentType);

        String resposeJson = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset());
        System.out.println(resposeJson);
        return resposeJson;
    }
}
