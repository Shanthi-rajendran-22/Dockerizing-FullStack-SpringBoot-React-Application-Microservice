package com.docker.restapimysql.helloworldmysql.serviceImpl;

import com.docker.restapimysql.helloworldmysql.model.HelloWorldModel;
import com.docker.restapimysql.helloworldmysql.repository.HelloWorldRepo;
import com.docker.restapimysql.helloworldmysql.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    private HelloWorldRepo repo;

    @Autowired
    public HelloWorldServiceImpl(HelloWorldRepo repo) {
        this.repo = repo;
    }

    @Override
    public HelloWorldModel getMessage() {
        List<HelloWorldModel> list = new ArrayList<HelloWorldModel>();
        list = repo.findAll();
        if(list.size()==0) {
            return repo.save(new HelloWorldModel("Hi.....Hello World Message From Server"));
        }
        else {
            return list.get(0);
        }
    }
}
