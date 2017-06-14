package com.fenniao.a3kezhi.Been;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

@DatabaseTable(tableName = "message")
public class MessageBean {
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(columnName = "type")
    String type;
    @DatabaseField(columnName = "conversationName")
    String conversationName;
    @DatabaseField(columnName = "direct")
    String direct;
    @DatabaseField(columnName = "value")
    String value;
    @DatabaseField(columnName = "longth")
    long longth=0;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getLongth() {
        return longth;
    }

    public void setLongth(long longth) {
        this.longth = longth;
    }
}
