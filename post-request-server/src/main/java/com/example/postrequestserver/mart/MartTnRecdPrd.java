package com.example.postrequestserver.mart;


import lombok.Data;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MartTnRecdPrd {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

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
    private Integer periodCo;
    private String wgtYn;

    public void insertMartTnRecdPrd(List<MartTnRecdPrd> tnRecdPrdList) {
        sqlSessionTemplate.insert("insertMartTnRecdPrd", tnRecdPrdList);
    }

}
