package com.becxagy.book.api.core.usecase;

import com.becxagy.book.api.application.command.UpdateChecklistCommand;

public interface UpdateBiddingUsecase {
    /**
     * Updates the bidding with the given ID using the provided command.
     *
     * @param biddingId the ID of the bidding to update
     * @param command   the command containing the updated details for the bidding
     */
    void updateChecklist(Long biddingId, UpdateChecklistCommand command);
    
}
