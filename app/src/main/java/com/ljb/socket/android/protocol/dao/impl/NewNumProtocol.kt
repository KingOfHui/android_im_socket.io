package com.ljb.socket.android.protocol.dao.impl

import android.content.ContentValues
import android.database.Cursor
import android.text.TextUtils
import com.ljb.socket.android.db.DatabaseSqlHelper
import com.ljb.socket.android.protocol.dao.INewNumDaoProtocol
import com.senyint.ihospital.user.kt.db.table.ImNewNumTable
import dao.ljb.kt.core.BaseDaoProtocol
import io.reactivex.Observable
import net.ljb.kt.utils.NetLog

/**
 * Author:Ljb
 * Time:2018/11/10
 * There is a lot of misery in life
 **/
class NewNumProtocol : BaseDaoProtocol(), INewNumDaoProtocol {

    override fun queryNewNum(table: ImNewNumTable, fromId: String, conversation: String): Observable<Int> = createObservable {
        queryNewNumImpl(table, fromId, conversation)
    }

    override fun insertNewNum(table: ImNewNumTable, num: Int, fromId: String, conversation: String): Observable<Boolean> = createObservable {
        insertNewNumImpl(table, num, fromId, conversation)
    }

    override fun deleteNewNum(table: ImNewNumTable, fromId: String, conversation: String) = createObservable {
        deleteNewsNumImpl(table, fromId, conversation) > 0
    }


    private fun insertNewNumImpl(table: ImNewNumTable, num: Int, fromId: String, conversation: String): Boolean {
        var result = false
        val newNum = queryNewNumImpl(table, fromId, conversation)
        if (newNum != -1) {
            val values = ContentValues()
            values.put(table.COLUMN_NEW_NUM, num + newNum)
            val where = DatabaseSqlHelper.getNewNumWhereSql(table, fromId, conversation)
            mSqliteDb.update(table.getName(), values, where, null)
        } else {
            val values = ContentValues()
            values.put(table.COLUMN_FROM_ID, fromId)
            values.put(table.COLUMN_CONVERSATION, conversation)
            values.put(table.COLUMN_NEW_NUM, num)
            mSqliteDb.insert(table.getName(), null, values)
        }
        return result
    }

    /***
     *   返回消息数
     */
    override fun queryNewNumImpl(table: ImNewNumTable, fromId: String, conversation: String): Int {
        var newNum = 0
        var cs: Cursor? = null
        try {
            val where = DatabaseSqlHelper.getNewNumWhereSql(table, fromId, conversation)
            var sql = "select * from ${table.getName()}"
            if (!TextUtils.isEmpty(where)) {
                sql += " where $where"
            }
            cs = mSqliteDb.rawQuery(sql, null)
            if (cs == null || cs.count == 0) {
                newNum = -1
            } else {
                while (cs.moveToNext()) {
                    newNum += cs.getInt(cs.getColumnIndex(table.COLUMN_NEW_NUM))
                }
            }
        } catch (e: Exception) {
            NetLog.e(e)
        } finally {
            cs?.close()
        }
        return newNum
    }


    private fun deleteNewsNumImpl(table: ImNewNumTable, fromId: String, conversation: String): Int {
        var result = 0
        try {
            val where = DatabaseSqlHelper.getNewNumWhereSql(table, fromId, conversation)
            result = mSqliteDb.delete(table.getName(), where, null)
        } catch (e: Exception) {
            NetLog.e(e)
        }
        return result
    }
}