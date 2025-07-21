package com.becxagy.book.api.application.mapper.checklist;

import com.becxagy.book.api.application.representation.checklist.ChecklistItemRepresentation;
import com.becxagy.book.api.core.domain.checklist.DocumentRequirement;

public class DocumentRequirementMapper {
    // This class is intended to map DocumentRequirement entities to their representations
    // and vice versa. It can be implemented using a library like MapStruct or manually.
    
    // Example method to convert a DocumentRequirement entity to its representation
    public static ChecklistItemRepresentation toRepresentation(DocumentRequirement documentRequirement) {
        return new ChecklistItemRepresentation(
            documentRequirement.getName(),
            documentRequirement.getExigenceStatus(),
            documentRequirement.getAdditionalInfo(),
            documentRequirement.getPossibleToAttach()
        );
    }

    // Example method to convert a representation back to an entity
    public static DocumentRequirement fromRepresentation(ChecklistItemRepresentation representation) {
        return new DocumentRequirement(
            representation.getName(),
            representation.getExigenceStatus(),
            representation.getAdditionalInfo(),
            representation.getPossibleToAttach()
        );
    }
}
