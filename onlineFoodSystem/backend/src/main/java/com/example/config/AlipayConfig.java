package com.example.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091500514740";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCA9HU4Q3fIhpmx9hYqcqm98qk7KPRz6rvcN6z24dI0qtINIuK2YsqAC1dlFwGJdh66HQjfrqebem81dZcjIgtyDD/vHWN6FZzzw//+SkqqycnYNYAtJBri72y3CYiGs61ZyN+zF47w5gV38lyG5KatXqfIdLS6afgmJDcMPVbX8mobTk9Hy92o+LGHDPHMA+R7+eiTdkaiSsY9KKDuIwalsZfn3kjzpRzpcG/kyPD+LHxwxkJnnvYI0V7aJyf82m7QiiQ9IgM1zbc4jmxRnfl8kCyOdPPHGPTny5CdCbJG81o/uonRWgaI8pEbjSlbXe+vh1bnmb0dR+j8cZcx3aPRAgMBAAECggEAU4sMLgk3nwfkprCBJ7rlURPWo2cjB735OvAu9DrUjh5kuvHw4qwZ+N5gHjPLYRzPR0/sIc3q9AVb6sxlv/McaySYfvcRLVS3PA2s2TeULBVS2OnAQn5zlLX8PpDEjE/3bIWAt2v5xQOgY1b2rsWPXh5GoM1LEMH6a+tmZl2O15Nfzp1TOseHj4zUl7qh2QzdpKUhn0C6rb9MJ4zPtd8Qdfv0ogLLRMC6wI1usf5CBZafeFbcDWrl+f+sxJQDBgdX220ZbufkRnBVKtX8U3we/5CYcYP3DYTl0uaz0+9kuURXNoNohSh8Wuor/4dGV94H6I80xIWDQZrORJL7zzKIAQKBgQC3udJBIr+voWexkIqpmf9+PUsZCfr42SPqZNDRU2YyIi9xwoPYvuaZNcmtSUkFWEjpWx4iBU9lsSIT58U9M+i9YGMMgQf4A+81fBwPi88GQYrKT/cJ5l6a50ZXC8CublpRSWYeeQYIoJgt8Z2ZNTE9sDcRAnLtQ0Y4BdTwFFLbQQKBgQCzruiQF3PZSN0H5PSp+LJSYvzd6LWSv4dkNwY2WtgwoDr5JFsYBX9EozzvZd4gCHqw9f/8X88RKcQph6TPiApd0QdcQPvRGs8JmPltj92r1MyusoVWpyWJJEvU8Y6E3HAtIk4bMpn/QwiMtXNR+mwgemSPbrHRK9zQ0JgwTBZ0kQKBgDyM6mV7LU+V01Xq/DgfP9v8GWb8KhGfhr22Ujfa06CMMbS9VbS2Xct6taf9sionyYfW9txhgaRTN5b12MRt+jOHs70oPVcY/NpUt+7LtgQqz7ITnqhNmzpUJJOFlRAn4G5P0W8TldB8FQt3SOh4Zm0dSVi6xOO7O0Y1bdNd71zBAoGBALOrZikPCmxvC3VlX7DGWalAChHrLAS9N6jhzHh1hk+5KL8Pt3bhqEz93WSUu/PUBrsEgR/evbMvzFKqrAZIxfThmzaIVETXRccXC/KeNM4pTzIwmFv5bvMfNCSOFjCMLC81alyP0KUKZnYsyk9i1NP1DrDNw0ihT4AN1OAWdS3BAoGBAJGfpkBqbDayQ2BCJJ4hEAlZZvt8rt5fkQlSHosc9FuGeyrnQjcyShamre7oBO1b7p4lh7TbhL5ewL9sZpdjYxR1z94o8KgGuyYtXUlZEnblxhxFv7L/+6hhAlU+PGkFgGYPYhWFjJBxXepbNoQSBQsFZq9bSwy6eybWA/OJUvqf";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2BW++0IHKExkVGF3KLwlmauXleELAuhIDNA+Kwl/R7znbGiGx4fisx/QGLZYtY+VzH65VWR85TrM6zuV228chdEwkjr4dBpBzfJB6dUentJLQO/tqcq/1B+B2tfiIc3Ax+MCq9hvrwAmsHIbr+0B8YfyHnO+FGxHfb/Z44cAFKbWNN80ezC4lLhz2/Sg8pjwu6H3GGhqGxRj5FqysbENlX2YVXCzRkWXs2I210kqFz9ZHkX/w8qOrJ7qtSSQAzqfl+rECxgBrtBQYMtsvZetSShCSK2z3CQwruT8LvwVYDXKhxSh0QoUXsmykZZIHd8FzQnTJYazQPZN0AkLdUPZeQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8081/#/front/orderResult";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8081/#/front/orderResult";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

