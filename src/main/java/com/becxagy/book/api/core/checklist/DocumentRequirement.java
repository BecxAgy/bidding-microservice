package com.becxagy.book.api.core.checklist;

import com.becxagy.book.api.core.bidding.Bidding;
import com.becxagy.book.api.infra.persistence.jpa.converter.ExigenceEnumConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "document_requirement")
public class DocumentRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "exigence_status")
    @Convert(converter = ExigenceEnumConverter.class)
    private ExigenceEnum exigenceStatus;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "possible_to_attach")
    private Boolean possibleToAttach;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding bidding;
}
