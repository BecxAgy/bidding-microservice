package com.becxagy.book.api.core.command;

public record PublishBiddingCommand(String fileName, Long biddingId) {
    // This record is used to encapsulate the command for publishing a bidding
    // It contains two fields, fileName and biddingId, which represent the name of the file to be published
    // and the ID of the bidding associated with the file, respectively.

}
