# Relatório de Contribuição Individual — Fase 02

Disciplina: Teste e Qualidade de Software
Projeto: Simulador de Batalha - Pokésal
Data: 27/09/2026

---

## João Gilberto Pereira dos Santos

- Diagnóstico do que faltava implementar em relação ao enunciado (seleção de iniciais, treinador, mochila, ambiente, motor de batalha, testes).
- Implementação de `FabricaPokesal`, com os 6 pokésal iniciais e seus movimentos.
- Implementação de `Treinador`, `Mochila` e `LimiteItensExcedidoException` (controle do limite de 2 itens por batalha).
- Implementação de `Ambiente` (efeitos de terreno e clima) e `Batalha` (ordem de ataque por velocidade, cálculo de dano, uso de item, efeitos de fim de turno).
- Configuração do JUnit 5 no `pom.xml` do projeto Maven.
- Resolução de problemas de integração no Git (merge de alterações, recuperação de arquivos, sincronização do repositório entre os membros da equipe).
- Elaboração do checklist de teste estático de código (revisão manual).
- Configuração do SonarQube no projeto, com instruções de execução documentadas no `README.md`.

## João Pedro de Mattos Cunha

- Implementação das classes base do domínio: `Pokesal`, `Movimento`, `TipoElemental`, `ItemSegurar`, `StatusPokesal`, `ItemMochila`, `EfeitoItemMochila`, `CategoriaItemMochila`, `Clima`, `Terreno`.
- Refinamento de `Pokesal` (alteração de `curar()` para retornar `boolean`, e de `aplicarStatus()` para impedir sobrescrita de status já ativo).
- Implementação de `FabricaItemMochila` (pacote `service`), com Poção, Cura de Queimadura, Antídoto e Cura de Paralisia, incluindo tratamento de casos de borda.
- Definição da regra de negócio adicional em `TipoElemental` (mesmo tipo contra mesmo tipo causa x0.5 de dano).
- Implementação dos 3 requisitos autorais: chance de acerto crítico, itens de segurar (Orbe de Vida, Capacete de Pedra, Colete de Ataque) e refinamento do sistema de terrenos e climas.
- Elaboração do teste estático dos requisitos da especificação original (apresentado ao professor).
- Escrita da maior parte da suíte de testes JUnit (`AmbienteTest`, `BatalhaTest`, `MochilaTest`, `PokesalTest`, entre outros).

---

## Observação

Este relatório foi elaborado com base no acompanhamento do desenvolvimento do projeto e na revisão do código-fonte. Recomenda-se conferir e complementar com o histórico real de commits do Git (`git log --oneline --author="nome"`) antes da entrega final, para validar e detalhar com precisão as contribuições de cada membro.
