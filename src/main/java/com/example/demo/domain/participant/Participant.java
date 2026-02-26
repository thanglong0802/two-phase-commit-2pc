package com.example.demo.domain.participant;

public interface Participant {
    boolean prepare();

    void commit();

    void rollback();
}
