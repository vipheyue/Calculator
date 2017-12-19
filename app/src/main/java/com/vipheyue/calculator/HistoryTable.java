package com.vipheyue.calculator;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by heyue on 2017/12/19.
 */

public class HistoryTable  extends RealmObject {
    @Required
    public String result;
    public String comment="";

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
