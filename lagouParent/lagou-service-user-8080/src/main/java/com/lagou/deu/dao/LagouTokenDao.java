package com.lagou.deu.dao;

import com.lagou.deu.entity.LagouToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LagouTokenDao extends JpaRepository<LagouToken,Integer> {

    public LagouToken findByEmail(String email);
    public LagouToken findByToken(String token);

}
