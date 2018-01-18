package com.vipheyue.calculator;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by heyue on 2017/12/19.
 */

public class HistoryTable  extends RealmObject {
    @Required
    public String result;
    public String comment="";
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    public HistoryTable() {
    }

    public HistoryTable(String result) {
        this.result = result;
    }

    public HistoryTable(String result, String comment) {
        this.result = result;
        this.comment = comment;
    }
}
