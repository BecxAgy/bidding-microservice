package com.becxagy.book.api.infra.utils.enumeration;

public interface ValueLabelEnum<C extends Enum<C>> {

    String getValue();

    String getLabel();
}