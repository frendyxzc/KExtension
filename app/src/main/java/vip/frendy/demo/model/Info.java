package vip.frendy.demo.model;

import android.content.Context;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by frendy on 2017/10/27.
 */
@Table("info")
public class Info implements Serializable {

    public static final String COL_ID = "_id";

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column(COL_ID)
    private int id;

    private HashMap<String, String> info;

    private long timestamp;

    public Info(HashMap<String, String> info, long timestamp) {
        this.info = info;
        this.timestamp = timestamp;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, String> info) {
        this.info = info;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(this.timestamp) + " " + this.info;
    }

    public static void save(Context context, Info data) {
        if(context != null) {
            DBManager.Companion.getInstance(context).save(data);
        }
    }

    public static ArrayList<Info> queryAll(Context context) {
        if(context != null) {
            return DBManager.Companion.getInstance(context).query(
                    new QueryBuilder<>(Info.class).appendOrderAscBy("timestamp"));
        }
        return new ArrayList<>();
    }

    public static void deleteAll(Context context) {
        if(context != null) {
            DBManager.Companion.getInstance(context).deleteAll(Info.class);
        }
    }

    public static void delete(Context context, ArrayList<Info> infos) {
        if(context == null || infos == null || infos.isEmpty()) return;

        for(Info info : infos) {
            DBManager.Companion.getInstance(context).delete(info);
        }
    }
}
