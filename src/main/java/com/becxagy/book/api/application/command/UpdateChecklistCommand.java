package com.becxagy.book.api.application.command;

import java.util.List;

import com.becxagy.book.api.application.representation.ChecklistItemRepresentation;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Comando para atualização do checklist de uma licitação")
public record UpdateChecklistCommand(
    @Schema(description = "Lista de itens do checklist")
    List<ChecklistItemRepresentation> checklistItems
) {}
