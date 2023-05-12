package com.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//季节枚举类
public enum SeasonsEnum {

    SPRING(1,"春天")
    ,SUMMER(2,"夏天")
    ,AUTUMN(3,"秋天")
    ,WINTER(4,"冬天");

    private int seasonsNum;

    private String seasonsContent;

    public static SeasonsEnum forEachSeasonsEnum(int i){
        SeasonsEnum[] arr=SeasonsEnum.values();
        for(SeasonsEnum seasonsEnum : arr){
            if(i==seasonsEnum.getSeasonsNum()){
                return seasonsEnum;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        for(int i=1;i<=4;i++){
            System.out.println(forEachSeasonsEnum(i)
                    +"\t"+forEachSeasonsEnum(i).getSeasonsNum()
                    +"\t"+forEachSeasonsEnum(i).getSeasonsContent());
        }
    }

}
