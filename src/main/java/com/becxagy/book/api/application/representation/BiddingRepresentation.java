
package com.becxagy.book.api.application.representation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiddingRepresentation {
    private Long id;
    private String name;
    private String description;
    private String fileUrl;
    private List<ChecklistItemRepresentation> checklist;
}