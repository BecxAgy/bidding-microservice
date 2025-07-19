package com.becxagy.book.api.shared.utils.enumeration;

public interface ValueLabelEnum<C extends Enum<C>> {

    String getValue();

    String getLabel();
}