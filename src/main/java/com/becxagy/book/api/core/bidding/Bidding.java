package com.becxagy.book.api.core.bidding;

import java.util.List;

import com.becxagy.book.api.core.checklist.DocumentRequirement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bidding {
    private Integer id;
    private String name;
    private String description;
    private String fileName;
    private List<DocumentRequirement> documentRequirements;
}
