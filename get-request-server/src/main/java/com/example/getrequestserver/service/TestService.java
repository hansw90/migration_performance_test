package com.example.getrequestserver.service;

import com.example.getrequestserver.dao.TestDao;
import com.example.getrequestserver.vo.TnRecdPrd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TestService {

    @Autowired
    TestDao testDao;

    public Collection<TnRecdPrd> getTestbyOffset(String start, String end) {
        return testDao.getTestbyOffset(start,end);
    }
}
