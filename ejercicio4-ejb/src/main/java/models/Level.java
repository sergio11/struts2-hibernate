/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
/**
 *
 * @author sergio
 */
public enum Level {
    EASY(1, "Easy"),MEDIUM(2, "Medium"),HIGHT(3, "Hight");
    
    private Integer code;
    private String name;
    
    private final static Integer DEFAULT_LEVEL =  1;
    private static final HashMap<Integer, Level> lookup = new HashMap<>();
     
    static {
        //Create reverse lookup hash map
        for (Level l : Level.values()) {
            lookup.put(l.getCode(), l);
        }
    }
    
    public static Level getFromValue(Integer code) {
        //the reverse lookup by simply getting
        //the value from the lookup HsahMap.
        return lookup.get(code) != null ? lookup.get(code) : lookup.get(DEFAULT_LEVEL);
    }

    private Level(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}
