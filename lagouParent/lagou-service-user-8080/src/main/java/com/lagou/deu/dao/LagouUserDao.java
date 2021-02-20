package com.lagou.deu.dao;

import com.lagou.deu.entity.LagouUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LagouUserDao extends JpaRepository<LagouUser,Integer> {

    public List<LagouUser> findByName(String name);

    public List<LagouUser> findByNameAndPassword(String name,String password);
}
