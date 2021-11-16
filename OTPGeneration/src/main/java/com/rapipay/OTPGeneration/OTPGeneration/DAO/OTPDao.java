package com.rapipay.OTPGeneration.OTPGeneration.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rapipay.OTPGeneration.OTPGeneration.Entity.OTPEntity;

@Repository
public interface OTPDao extends JpaRepository<OTPEntity, String> {

}
