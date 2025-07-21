package com.becxagy.book.api.application.representation.checklist;

import com.becxagy.book.api.core.domain.checklist.ExigenceEnum;
import com.becxagy.book.api.infra.out.persistence.jpa.converter.ExigenceEnumConverter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Item do checklist de uma licitação")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistItemRepresentation {
    
    @Schema(description = "Nome do item do checklist", example = "Documentação da empresa (CNPJ, contrato social)")
    private String name;

    @Schema(description = "Status da exigência", example = "OBRIGATORIO", allowableValues = {"OPCIONAL", "OBRIGATORIO", "CONDICIONAL"})
    private ExigenceEnum exigenceStatus;

    @Schema(description = "Informações adicionais sobre o item", example = "Documentos atualizados dos últimos 90 dias")
    private String additionalInfo;

    @Schema(description = "Indica se é possível anexar documentos para este item", example = "true")
    private Boolean possibleToAttach;
}
