package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.EmailDetails;

public interface EmailService {
    void sendEMailAlert(EmailDetails emailDetails);
}
