package com.becxagy.book.api.core.domain.checklist;

import com.becxagy.book.api.shared.utils.enumeration.ValueLabelEnum;

public enum ExigenceEnum implements ValueLabelEnum<ExigenceEnum> {
    OPCIONAL("Opcional"),
    OBRIGATORIO("Obrigatório"),
    CONDICIONAL("Condicional");

    private final String label;

    ExigenceEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getValue() {
        return name();
    }
    

}
