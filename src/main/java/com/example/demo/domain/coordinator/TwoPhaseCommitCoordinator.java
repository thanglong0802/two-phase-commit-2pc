package com.example.demo.domain.coordinator;

import com.example.demo.domain.participant.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwoPhaseCommitCoordinator {

    private static final Logger log = LoggerFactory.getLogger(TwoPhaseCommitCoordinator.class);

    public void execute(List<Participant> participants) {
        // Implementation of the two-phase commit protocol

        // Phase 1: Prepare
        for (Participant participant : participants) {
            boolean prepared = participant.prepare();
            if (!prepared) {
                // If any participant fails to prepare, abort the transaction
                for (Participant p : participants) {
                    p.rollback();
                    log.error("Transaction aborted during prepare phase. Participant {} failed to prepare.", p.getClass().getName());
                }
                return;
            }
        }

        // Phase 2: Commit
        for (Participant participant : participants) {
            participant.commit();
        }
    }

}
