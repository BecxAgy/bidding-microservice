package com.becxagy.book.api.application.command;

import java.util.List;

import com.becxagy.book.api.application.representation.ChecklistItemRepresentation;

public record UpdateChecklistCommand(List<ChecklistItemRepresentation> checklistItems) {


}
