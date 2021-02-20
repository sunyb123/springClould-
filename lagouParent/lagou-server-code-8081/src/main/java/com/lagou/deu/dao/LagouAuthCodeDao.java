package com.lagou.deu.dao;

import com.lagou.deu.entity.LagouAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LagouAuthCodeDao extends JpaRepository<LagouAuthCode,Integer> {
    public List<LagouAuthCode> findByEmailAndCode(String email,String code);
}
