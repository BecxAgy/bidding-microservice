package com.becxagy.book.api.application.representation;

import com.becxagy.book.api.core.domain.checklist.ExigenceEnum;
import com.becxagy.book.api.infra.out.persistence.jpa.converter.ExigenceEnumConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistItemRepresentation {
    private String name;

    private ExigenceEnum exigenceStatus;

    private String additionalInfo;

    private Boolean possibleToAttach;

   
    
}
