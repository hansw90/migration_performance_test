package com.example.getrequestserver.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TnRecdPrd {
    private String orgId;
    private String tblId;
    private String prdSe;
    private String prdDe;
    private String cllctKd;
    private String stblTrnsSe;
    private String pubSe;
    private String smblCn;
    private String dtlDc;
    private String fstRegDe;
    private String fstRegNm;
    private String lstChnDe;
    private String lstChnNm;
    private String sendDe;
    private String sendNm;
    private int periodCo;
    private String wgtYn;
}
