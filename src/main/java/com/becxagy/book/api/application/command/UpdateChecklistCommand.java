package com.becxagy.book.api.application.command;

import java.util.List;

import com.becxagy.book.api.application.representation.ChecklistItemRepresentation;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Comando para atualização do checklist de uma licitação")
public record UpdateChecklistCommand(
    @Schema(description = "Lista de itens do checklist")
    @NotEmpty(message = "Checklist items cannot be empty")
    @NotNull(message = "Checklist items are required")
    List<ChecklistItemRepresentation> checklistItems
) {}
