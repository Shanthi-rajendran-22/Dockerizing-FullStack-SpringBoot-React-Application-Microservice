package com.docker.restapimysql.helloworldmysql.repository;

import com.docker.restapimysql.helloworldmysql.model.HelloWorldModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HelloWorldRepo extends JpaRepository<HelloWorldModel,Long>{
}
