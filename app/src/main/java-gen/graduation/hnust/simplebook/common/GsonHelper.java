package graduation.hnust.simplebook.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * util for Object <==> JSON
 *
 * @Author : panxin
 * @Date   : 05:31 PM 3/26/16
 * @Email  : panxin109@gmail.com
 */
public class GsonHelper {

    private static Gson gson;
    private static Gson gsonList;

    static {
        // create the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();
        // register an adapter to manager the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        gson = builder.create();
    }

    /**
     * bean to json
     *
     * @param t Bean Class
     * @param <T> T
     * @return json
     */
    public static <T> String bean2Json(T t) {
        return gson.toJson(t);
    }

    /**
     * json to bean
     *
     * @param json json
     * @param t target class
     * @param <T> T
     * @return T
     */
    public static <T> T json2Bean(String json, Class<T> t) {
        return gson.fromJson(json, t);
    }

    /**
     * Json to List
     *
     * @param json json
     * @param t target object in the list
     * @param <T> t
     * @return  result of the list
     */
    public <T> T json2List(String json, List<T> t) {
        Type listType = new TypeToken<List<T>>(){
            // ...
        }.getType();
        return gsonList.fromJson(json, listType);
    }

}
