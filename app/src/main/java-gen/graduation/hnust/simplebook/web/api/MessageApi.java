package graduation.hnust.simplebook.web.api;

/**
 * @Author : panxin109@gmail.com
 * @Date : 1:39 PM 4/30/16
 */
public class MessageApi {

    /**
     * 发送短信
     */
    public static final String SEND_SMS = "/api/msg/send_sms";

    /**
     * 校验短信验证码
     */
    public static final String VERIFY_SMS_CODE = "/api/msg/verify_sms";

}
