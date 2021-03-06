package sign.http;

import com.tal.ailab.enums.RequestMethod;
import sign.http.application_json.HttpSignDemo;

/**
 * http测试入口
 * 修改：import sign.http.application_json.HttpSignDemo完成对应请求
 */
public class TestDemo {

    public static void main(String[] args) {
        try {
            /**填写自己AK
             * 获取AK教程：https://openai.100tal.com/documents/article/page?fromWhichSys=admin&id=27
             * */
            String access_key_id = "666--------------";
            String access_key_secret = "888---------------";

            //请求URL，请替换自己的真实地址
            String url = "http://openai.100tal.com/ai-----/----";

            //请求方式
            RequestMethod requestMethod = RequestMethod.POST;

            HttpSignDemo.sendPostOrPatchOrPut(access_key_id,access_key_secret,url,requestMethod);

            requestMethod = RequestMethod.GET;
//            HttpSignDemo.sendGetOrDelete(access_key_id,access_key_secret,url,requestMethod);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
