# Checklist de Teste Estático de Código (Revisão Manual)

Disciplina: Teste e Qualidade de Software
Projeto: Simulador de Batalha - Pokésal
Discentes: João Gilberto, João Pedro
Data da revisão: 27/09/2026

---

**1. Todas as variáveis estão inicializadas antes do uso?**

Sim. Todos os construtores (`Pokesal`, `Treinador`, `Mochila`, `Batalha`, `Ambiente`) inicializam os campos explicitamente.

**2. Há variáveis declaradas e nunca usadas?**

Não foram encontradas na revisão manual das classes de `model` e `service`.

**3. Existe código inacessível?**

Não há código morto. O `default: throw new IllegalArgumentException(...)` em `FabricaPokesal.criar()` nunca é alcançado hoje (o enum só tem os 6 valores válidos), mas foi mantido como proteção defensiva.

**4. Há código duplicado?**

Sim, encontrado e corrigido. Existiam duas fábricas de item de mochila (`ItensMochilaPadrao` e `FabricaItemMochila`). Removemos `ItensMochilaPadrao` e mantivemos `FabricaItemMochila`, que era mais completa.

**5. Existem erros de sintaxe?**

Não. O projeto compila sem erros.

**6. Há erros de lógica que quebram regras do negócio?**

Um ponto de atenção: em `TipoElemental`, Fogo×Fogo, Água×Água e Planta×Planta causam x0.5 de dano. Isso não está no enunciado original, mas foi confirmado com a equipe como decisão intencional, não erro.

**7. Há erros de tipagem?**

Não.

**8. O fluxo de controle é válido (sem loops infinitos, condições impossíveis)?**

Sim. O `Main.java` usa um limite de turnos como proteção contra loop infinito, caso nenhum pokésal desmaie.

**9. Outros erros identificados**

- `Pokesal.curar()` mudou de `void` para `boolean`, e `Pokesal.aplicarStatus()` passou a impedir sobrescrever um status já ativo. Mudanças conscientes, já testadas.
- Dos 3 requisitos autorais, só o de terreno/clima tem teste dedicado. Crítico e itens de segurar ainda não têm teste específico.
- `Ambiente.sortear(Random)` e `Batalha.houveCritico()` recebem o `Random` por fora (injeção de dependência), facilitando testes determinísticos no futuro.

---

Assinaturas:

João Gilberto: João Gilberto Pereira dos Santos

João Pedro: João Pedro de Mattos Cunha
