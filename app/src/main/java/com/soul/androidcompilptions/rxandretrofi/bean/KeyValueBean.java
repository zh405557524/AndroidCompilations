package com.soul.androidcompilptions.rxandretrofi.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.annotation.Unique;

/**
 * k-v pair
 * Created by MEI on 2017/4/10.
 */
@Table("KeyValueBean")
public class KeyValueBean extends BaseBean {

    @NotNull
    @Unique
    private String key;

    @NotNull
    private String value;

    public KeyValueBean(String key, String val) {
        this.key = key;
        this.value = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KeyValueBean{" +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
