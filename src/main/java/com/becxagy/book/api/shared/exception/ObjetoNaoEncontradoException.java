package com.becxagy.book.api.shared.exception;

import com.becxagy.book.api.shared.internacionalization.Mensagens;

public class ObjetoNaoEncontradoException extends BiddingBusinessException{

    private static final long serialVersionUID = 1L;

    public ObjetoNaoEncontradoException() {
        super(Mensagens.get("objeto-nao-encontrado"));
    }
}