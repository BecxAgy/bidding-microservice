package com.becxagy.book.api.infra.in;


import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.becxagy.book.api.application.command.CreateBiddingCommand;
import com.becxagy.book.api.application.command.UpdateChecklistCommand;
import com.becxagy.book.api.application.representation.BiddingRepresentation;
import com.becxagy.book.api.core.usecase.CreateBiddingUsecase;
import com.becxagy.book.api.core.usecase.ReadBiddingUsecase;
import com.becxagy.book.api.core.usecase.UpdateBiddingUsecase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Bidding API", description = "API para gerenciamento de licitações")
@RestController()
@RequestMapping("/v1/bidding")
public class BiddingResource {

    private final CreateBiddingUsecase uploadBiddingUsecase;
    private final ReadBiddingUsecase readBiddingUsecase;
    private final UpdateBiddingUsecase updateBiddingUsecase;

    @Autowired
    public BiddingResource(CreateBiddingUsecase uploadBiddingUsecase, ReadBiddingUsecase readBiddingUsecase, UpdateBiddingUsecase updateBiddingUsecase) {
        this.uploadBiddingUsecase = uploadBiddingUsecase;
        this.readBiddingUsecase = readBiddingUsecase;
        this.updateBiddingUsecase = updateBiddingUsecase;
    }

    @Operation(
        summary = "Criar nova licitação",
        description = "Cria uma nova licitação enviando um arquivo PDF e metadados",
        requestBody = @RequestBody(
            description = "Dados da licitação (form-data)",
            required = true,
            content = @Content(
                mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                schema = @Schema(implementation = CreateBiddingCommand.class),
                examples = @ExampleObject(
                    name = "Exemplo de criação",
                    description = "Exemplo de dados para criar uma licitação",
                    value = """
                    {
                        "file": "(arquivo PDF)",
                        "name": "Licitação para Construção de Escola",
                        "description": "Licitação pública para construção de escola municipal com 10 salas de aula"
                    }
                    """
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Licitação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadBidding(@Valid @ModelAttribute("file")  CreateBiddingCommand command){
        uploadBiddingUsecase.create(command);
        return ResponseEntity.ok("Bidding created successfully");
    }

    @Operation(
        summary = "Atualizar checklist da licitação",
        description = "Atualiza o checklist de uma licitação existente com novos itens de documentação",
        requestBody = @RequestBody(
            description = "Lista de itens do checklist",
            required = true,
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UpdateChecklistCommand.class),
                examples = @ExampleObject(
                    name = "Exemplo de checklist",
                    description = "Exemplo de itens para atualizar o checklist",
                    value = """
                    {
                        "checklistItems": [
                            {
                                "name": "Documentação da empresa (CNPJ, contrato social)",
                                "exigenceStatus": "OBRIGATORIO",
                                "additionalInfo": "Documentos atualizados dos últimos 90 dias",
                                "possibleToAttach": true
                            },
                            {
                                "name": "Atestado de capacidade técnica",
                                "exigenceStatus": "OPCIONAL",
                                "additionalInfo": "Comprovação de experiência em projetos similares",
                                "possibleToAttach": true
                            }
                        ]
                    }
                    """
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Checklist atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Licitação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PatchMapping("/checklist/{biddingId}")
    public ResponseEntity<String> updateBiddingChecklist(
            @Parameter(description = "ID da licitação", required = true, example = "1")
            @PathVariable Long biddingId, 
            @RequestBody UpdateChecklistCommand command) {
        updateBiddingUsecase.updateChecklist(biddingId, command);
        return ResponseEntity.ok("Bidding checklist updated successfully");
    }
    
    @Operation(
        summary = "Buscar licitação por ID",
        description = "Recupera uma licitação específica através do seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Licitação encontrada com sucesso",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = BiddingRepresentation.class)
            )
        ),
        @ApiResponse(responseCode = "404", description = "Licitação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{biddingId}")
    public ResponseEntity<BiddingRepresentation> getBidding(
            @Parameter(description = "ID da licitação", required = true, example = "1")
            @PathVariable Long biddingId) {
        BiddingRepresentation bidding = readBiddingUsecase.get(biddingId);
        return ResponseEntity.ok(bidding);
    }
    
    @Operation(
        summary = "Listar todas as licitações",
        description = "Recupera todas as licitações com suporte à paginação e ordenação"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de licitações recuperada com sucesso",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Page.class)
            )
        ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/")
    public ResponseEntity<Page<BiddingRepresentation>> getAllBiddings(
            @Parameter(description = "Número da página (inicia em 0)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size
           
           ) {
        Pageable pageable = PageRequest.of(page, size); 
        Page<BiddingRepresentation> biddings = readBiddingUsecase.getAll(pageable);
        return ResponseEntity.ok(biddings);
    }

}
