package vip.frendy.demo.model

import android.content.Context
import com.litesuits.orm.LiteOrm

/**
 * Created by frendy on 2017/10/27.
 */
class DBManager {

    companion object {
        private val DB_NAME = "demo.db"
        private var manager: LiteOrm? = null

        fun getInstance(context: Context): LiteOrm {
            if(manager == null) {
                manager = LiteOrm.newSingleInstance(context.applicationContext, DB_NAME)
            }
            return manager!!
        }
    }
}