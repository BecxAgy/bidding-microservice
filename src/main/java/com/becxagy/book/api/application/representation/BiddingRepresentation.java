
package com.becxagy.book.api.application.representation;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Representação de uma licitação")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiddingRepresentation {
    
    @Schema(description = "ID único da licitação", example = "1")
    private Long id;
    
    @Schema(description = "Nome da licitação", example = "Licitação para Construção de Escola")
    private String name;
    
    @Schema(description = "Descrição detalhada da licitação", example = "Licitação pública para construção de escola municipal com 10 salas de aula")
    private String description;
    
    @Schema(description = "URL do arquivo da licitação", example = "https://s3.amazonaws.com/bucket/licitacao.pdf")
    private String fileUrl;
    
    @Schema(description = "Lista de itens do checklist da licitação")
    private List<ChecklistItemRepresentation> checklist;
}