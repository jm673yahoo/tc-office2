package ca.mydemo.tcoffice.domain;

import java.io.Serializable;

public class BasicItem implements Serializable {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicItem(String code, String name) {
        setCode(code);
        setName(name);
    }


}
