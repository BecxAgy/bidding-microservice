package com.becxagy.book.api.application.command.bidding;

import org.springframework.web.multipart.MultipartFile;

import com.becxagy.book.api.shared.utils.validation.ValidFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Comando para criação de uma nova licitação")
public record CreateBiddingCommand (
    @Schema(description = "Arquivo PDF da licitação", type = "string", format = "binary")
    @ValidFile 
    @NotNull(message = "File is required") 
    MultipartFile file, 
    
    @Schema(description = "Nome da licitação", example = "Licitação para Construção de Escola")
    @NotBlank 
    String name, 
    
    @Schema(description = "Descrição detalhada da licitação", example = "Licitação pública para construção de escola municipal com 10 salas de aula")
    @NotBlank 
    String description,

    @Pattern(regexp = "^(gemma|deepseek|dolphin)$", 
             message = "Model deve ser um dos seguintes: gemma, deepseek, dolphin")
    @Schema(description = "Modelo LLM para processar licitação", 
            example = "gemma", 
            allowableValues = {"gemma", "deepseek", "dolphin"})
    String model
){}
