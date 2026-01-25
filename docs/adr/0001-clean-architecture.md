# ADR 0001 — Adoção de Clean Architecture

## Contexto
O projeto `unified-eats-fase-2` precisa ser fácil de evoluir, testável e manter separação clara entre regra de negócio e detalhes técnicos (framework, banco, integrações). Além disso, deve suportar crescimento incremental de funcionalidades sem acoplamento excessivo.

## Decisão
Adotar Clean Architecture, organizando o código em camadas:

- **Domain**: entidades e regras de negócio puras, sem dependência de frameworks.
- **Application**: casos de uso e orquestração de operações do domínio.
- **Interface/API**: controllers e DTOs, apenas entrada e saída, sem regra de negócio.
- **Infrastructure**: persistência e integrações, implementando interfaces (ports) definidas nas camadas internas.

Regras de dependência:
- Camadas externas podem depender das internas.
- Camadas internas não podem depender das externas.
- `domain` não depende de ninguém.

## Consequências

### Positivas
- Aumento da testabilidade (casos de uso testáveis sem Spring).
- Melhor separação de responsabilidades.
- Facilidade de evolução do domínio.

### Negativas / Custos
- Mais estrutura inicial.
- Necessidade de disciplina para manter as regras de dependência.