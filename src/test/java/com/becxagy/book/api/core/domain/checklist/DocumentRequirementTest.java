package com.becxagy.book.api.core.domain.checklist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.becxagy.book.api.core.domain.bidding.Bidding;

class DocumentRequirementTest {

    @Test
    void shouldCreateDocumentRequirementWithAllFields() {
        // Given
        DocumentRequirement requirement = new DocumentRequirement();
        requirement.setId(1L);
        requirement.setName("CPF");
        requirement.setExigenceStatus(ExigenceEnum.OBRIGATORIO);
        requirement.setAdditionalInfo("Documento de identificação");
        requirement.setPossibleToAttach(true);
        
        Bidding bidding = new Bidding();
        bidding.setId(1L);
        requirement.setBidding(bidding);

        // Then
        assertEquals(1L, requirement.getId());
        assertEquals("CPF", requirement.getName());
        assertEquals(ExigenceEnum.OBRIGATORIO, requirement.getExigenceStatus());
        assertEquals("Documento de identificação", requirement.getAdditionalInfo());
        assertTrue(requirement.getPossibleToAttach());
        assertEquals(1L, requirement.getBidding().getId());
    }

    @Test
    void shouldCreateDocumentRequirementWithMinimalFields() {
        // Given
        DocumentRequirement requirement = new DocumentRequirement();
        requirement.setName("RG");
        requirement.setExigenceStatus(ExigenceEnum.OPCIONAL);

        // Then
        assertEquals("RG", requirement.getName());
        assertEquals(ExigenceEnum.OPCIONAL, requirement.getExigenceStatus());
        assertNull(requirement.getId());
        assertNull(requirement.getAdditionalInfo());
        assertNull(requirement.getPossibleToAttach());
        assertNull(requirement.getBidding());
    }
}
