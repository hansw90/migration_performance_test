package com.example.getrequestserver.dao;

import com.example.getrequestserver.vo.TnRecdPrd;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TestDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public Collection<TnRecdPrd> getTestbyOffset(String start, String end) {
        // userMapper 라는 부분과 5단계에 있는 mapper.xml 파일의 namespace를 동일하게 맞춰준다
        //.getUserInfo 와 5단계에 있는 <select id= 부분를 동일하게 맞춰준다.
        Map<String , Integer> param = new HashMap();
        param.put("start", Integer.valueOf(start));
        param.put("end", Integer.valueOf(end));
        return sqlSessionTemplate.selectList("testMapper.getTestbyOffset", param);

    }

}
