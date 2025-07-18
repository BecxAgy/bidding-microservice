package com.becxagy.book.api.core.checklist;

import com.becxagy.book.api.core.bidding.Bidding;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentRequirement {
    private Long id;
    private String name;
    private ExigenceEnum exigenceStatus;
    private String additionalInfo;
    private Boolean possibleToAttach;
    private Bidding bidding;
}
