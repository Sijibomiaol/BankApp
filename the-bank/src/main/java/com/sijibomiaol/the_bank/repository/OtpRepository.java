package com.sijibomiaol.the_bank.repository;

import com.sijibomiaol.the_bank.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Otp findByEmail(String email);

}
