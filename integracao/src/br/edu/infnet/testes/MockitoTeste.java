package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import br.edu.infnet.service.AutorizadorInscricao;

public class MockitoTeste {

    @Test
    void deveCriarMock() {

        // Cria o Mock.
        AutorizadorInscricao autorizador =
            mock(AutorizadorInscricao.class);

        // Verifica se o Mock foi criado.
        assertNotNull(autorizador);

        // Não deve ter recebido chamadas.
        verifyNoInteractions(autorizador);
    }
}
