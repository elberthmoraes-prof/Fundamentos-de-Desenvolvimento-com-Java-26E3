
package br.edu.infnet.testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.edu.infnet.model.Evento;

@DisplayName("Testes unitários da classe Evento")
public class EventoTeste {

    /**
     * Grupo 1: criação e validação de eventos.
     */
    @Nested
    @DisplayName("Criação e validação")
    class CriacaoEValidacao {

        @Test
        @DisplayName("ET-003 - Deve rejeitar capacidade zero")
        void deveRejeitarCapacidadeZero() {

            // ARRANGE
            int capacidadeInvalida = 0;

            // ACT + ASSERT
            assertThrows(
                IllegalArgumentException.class,
                () -> new Evento("Java Day", "Infnet", capacidadeInvalida)
            );
        }

        @Test
        @DisplayName("ET-006 - Deve rejeitar capacidade negativa")
        void deveRejeitarCapacidadeNegativa() {

            // ARRANGE
            int capacidadeInvalida = -5;

            // ACT
            IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Evento("Java Day", "Infnet", capacidadeInvalida)
            );

            // ASSERT
            assertEquals(
                "A capacidade deve ser maior que zero.",
                excecao.getMessage()
            );
        }

        @Test
        @DisplayName("ET-007 - Deve rejeitar nome em branco")
        void deveRejeitarNomeEmBranco() {

            // ARRANGE
            String nomeInvalido = "   ";

            // ACT + ASSERT
            assertThrows(
                IllegalArgumentException.class,
                () -> new Evento(nomeInvalido, "Infnet", 10)
            );
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -5, -100})
        @DisplayName("ET-011 - Deve rejeitar capacidades inválidas")
        void deveRejeitarCapacidadesInvalidas(int capacidade) {

            // ACT + ASSERT
            assertThrows(
                IllegalArgumentException.class,
                () -> new Evento("Java Day", "Infnet", capacidade)
            );
        }
    }

    /**
     * Grupo 2: disponibilidade de vagas.
     */
    @Nested
    @DisplayName("Disponibilidade de vagas")
    class DisponibilidadeDeVagas {

        private Evento evento;

        @BeforeEach
        void prepararCenario() {
            evento = new Evento("Java Day", "Infnet", 10);
        }

        @Test
        @DisplayName("ET-001 - Evento vazio deve possuir vagas")
        void devePossuirVagasQuandoNaoHaInscritos() {

            // ARRANGE: realizado pelo @BeforeEach

            // ACT
            boolean possuiVaga = evento.temVaga();

            // ASSERT
            assertTrue(possuiVaga);
        }

        @Test
        @DisplayName("ET-002 - Evento lotado não possui vagas")
        void naoDevePossuirVagasQuandoEventoEstaLotado() {

            // ARRANGE: cenário específico com duas vagas
            Evento eventoLotado = new Evento(
                "Java Day", "Infnet", 2);

            eventoLotado.inscrever();
            eventoLotado.inscrever();

            // ACT
            boolean possuiVaga = eventoLotado.temVaga();

            // ASSERT
            assertFalse(possuiVaga);
        }

        @Test
        @DisplayName("ET-010 - Deve respeitar capacidade mínima")
        void deveRespeitarCapacidadeMinima() {

            // ARRANGE
            Evento eventoMinimo = new Evento(
                "Java Day", "Infnet", 1);

            // ACT
            eventoMinimo.inscrever();
            eventoMinimo.inscrever();

            // ASSERT
            assertAll(
                "Capacidade mínima",

                () -> assertEquals(
                    1, eventoMinimo.getQtdeInscritos()),

                () -> assertFalse(eventoMinimo.temVaga())
            );
        }
        
        @Test
        @DisplayName("ET-015A - Deve identificar evento vazio")
        void deveIdentificarEventoVazio() {
        	
        	// ACT
        	String estadoOcupacao = evento.getEstadoOcupacao();
        	
        	// ASSERT
        	assertEquals("VAZIO", estadoOcupacao);
        }
        
        @Test
        @DisplayName("ET-015B - Deve identificar evento parcialmente ocupado")
        void deveIdentificarEventoParcialmenteOcupado(){
        	
        	// ARRANGE
        	evento.inscrever();
        	evento.inscrever();
        	evento.inscrever();
        	
        	// ACT
        	String estadoOcupacao = evento.getEstadoOcupacao();
        	
        	// ASSERT
        	assertEquals("PARCIALMENTE_OCUPADO", estadoOcupacao);
        }
        
        @Test
        @DisplayName("ET-015C - Deve identificar evento lotado")
        void deveIdentificarEventoLotado() {
        
        	// ARRANGE
        	Evento evento = new Evento("Java Day", "Infnet", 2);
        	evento.inscrever();
        	evento.inscrever();
        	
        	// ACT
        	String estadoOcupacao = evento.getEstadoOcupacao();
        	
        	// ASSERT
        	assertEquals("LOTADO", estadoOcupacao);
        }
        
    }

    /**
     * Grupo 3: realização de inscrições.
     */
    @Nested
    @DisplayName("Inscrições")
    class Inscricoes {

        private Evento evento;

        @BeforeEach
        void prepararCenario() {
            evento = new Evento("Java Day", "Infnet", 10);
        }

        @Test
        @DisplayName("ET-004 - Deve incrementar inscritos")
        void deveIncrementarQuantidadeDeInscritos() {

            // ACT
            evento.inscrever();

            // ASSERT
            assertEquals(1, evento.getQtdeInscritos());
        }

        @Test
        @DisplayName("ET-005 - Não deve ultrapassar capacidade")
        void naoDeveUltrapassarCapacidadeMaxima() {

            // ARRANGE
            Evento eventoLimitado = new Evento(
                "Java Day", "Infnet", 2);

            eventoLimitado.inscrever();
            eventoLimitado.inscrever();

            // ACT
            eventoLimitado.inscrever();

            // ASSERT
            assertEquals(
                2, eventoLimitado.getQtdeInscritos());
        }

        @Test
        @DisplayName("ET-008A - Deve registrar uma inscrição")
        void deveRegistrarUmaInscricaoComFixture() {

            // ACT
            evento.inscrever();

            // ASSERT
            assertEquals(1, evento.getQtdeInscritos());
        }

        @Test
        @DisplayName("ET-008B - Deve iniciar sem inscritos")
        void deveIniciarSemInscritos() {

            // ASSERT
            assertEquals(0, evento.getQtdeInscritos());
        }

        @Test
        @DisplayName("ET-012 - Deve informar sucesso na inscrição")
        void deveInformarSucessoNaInscricao() {

            // ACT
            boolean resultado = evento.inscrever();

            // ASSERT
            assertTrue(resultado);
        }

        @Test
        @DisplayName("ET-012 - Deve informar falha quando lotado")
        void deveInformarFalhaQuandoLotado() {

            // ARRANGE
            Evento eventoLotado = new Evento("Java Day", "Infnet", 1);

            eventoLotado.inscrever();

            // ACT
            boolean resultado = eventoLotado.inscrever();

            // ASSERT
            assertAll(
                "Inscrição recusada",

                () -> assertFalse(resultado),

                () -> assertEquals(
                    1, eventoLotado.getQtdeInscritos())
            );
        }
        
        @Test
        @DisplayName("ET-013 - Deve calcular o percentual de ocupação")
        void deveCalcularPercentualDeOcupacao() {
        	
        	// ARRANGE
        	Evento evento = new Evento("Java Day", "Infnet", 3);
        	evento.inscrever();
        	
        	double percentualEsperado = 33.3333;
        	double tolerancia = 0.0001;
        	
        	// ACT
        	double percentualAtual = evento.getPercentualOcupacao();
        	
        	// ASSERT
        	assertEquals(percentualEsperado, percentualAtual, tolerancia, "O percentual de ocupação está incorreto");
        }
    }

    /**
     * Grupo 4: verificação do estado inicial.
     */
    @Nested
    @DisplayName("Estado inicial")
    class EstadoInicial {

        private Evento evento;

        @BeforeEach
        void prepararCenario() {
        	System.out.println("1 - SETUP");
        	
            evento = new Evento("Java Day", "Infnet", 10);
        }
        
        @Test
        @DisplayName("???")
        void deveApresentarToString() {
        	
        	String mensagemEsperada = "nome=Java Day; local=Infnet; capacidade=10; qtdeInscritos=0";
        	
        	String mensagem = evento.toString();
        	
        	assertEquals(mensagemEsperada, mensagem);
        }

        @Test
        @DisplayName("ET-009 - Deve iniciar com estado válido")
        void deveIniciarEventoComEstadoValido() {

            // ACT
        	System.out.println("2 - EXECUTION");
        	
            int capacidade = evento.getCapacidade();
            int inscritos = evento.getQtdeInscritos();
            boolean possuiVaga = evento.temVaga();

            // ASSERT
        	System.out.println("3 - ASSERTION");
            
            assertAll(
                "Estado inicial do evento",

                () -> assertEquals(10, capacidade),

                () -> assertEquals(0, inscritos),

                () -> assertTrue(possuiVaga)
            );
        }
        
        @AfterEach
        void finalizarCenario() {
        	System.out.println("4 - TEARDOWN");

        	System.out.println("");
        }
    }
}