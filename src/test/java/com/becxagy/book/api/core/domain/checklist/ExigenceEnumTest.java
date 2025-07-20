package com.becxagy.book.api.core.domain.checklist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExigenceEnumTest {

    @Test
    void shouldReturnCorrectLabels() {
        assertEquals("Opcional", ExigenceEnum.OPCIONAL.getLabel());
        assertEquals("Obrigatório", ExigenceEnum.OBRIGATORIO.getLabel());
        assertEquals("Condicional", ExigenceEnum.CONDICIONAL.getLabel());
    }

    @Test
    void shouldReturnCorrectValues() {
        assertEquals("OPCIONAL", ExigenceEnum.OPCIONAL.getValue());
        assertEquals("OBRIGATORIO", ExigenceEnum.OBRIGATORIO.getValue());
        assertEquals("CONDICIONAL", ExigenceEnum.CONDICIONAL.getValue());
    }

    @Test
    void shouldHaveThreeEnumValues() {
        ExigenceEnum[] values = ExigenceEnum.values();
        assertEquals(3, values.length);
    }
}
