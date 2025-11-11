# IRONHIDE #26956

Este repositório contém o código-fonte oficial da equipe IRONHIDE #26956 para a temporada atual da FIRST Tech Challenge (FTC) - Decode.
O robô SCORPION foi projetado para oferecer desempenho consistente em movimentação, arremesso de projéteis e coleta automática, utilizando um sistema modular em Java baseado no SDK oficial da FTC.

---

## Organização do Projeto

``` bash
TeamCode/
├── src/main/java/org/firstinspires/ftc/teamcode/
│   ├── drive/                      → Controle básico de movimentação
│   ├── opmodes/
│   │   ├── auto/                   → Modos autônomos
│   │   └── teleop/                 → Modos de teleoperação
│   ├── subsystems/                 → Controle dos subsistemas do robô
│   └── util/                       → Auxiliar valores do PID, ângulos, etc
```

---

## Estrutura Modular

Cada subsistema do robô é independente e pode ser testado ou reutilizado separadamente.
Isso facilita o desenvolvimento em equipe e a manutenção do código.

| Pacote | Função |
|:-------|:--------|
| `drive` | Controle dos motores principais |
| `opmodes` | Lógica de teleop e autônomo |
| `subsystems` | Componentes físicos (Shooter, Intake etc.) |
| `util` | Funções auxiliares (PID, constantes, etc.) |

---

## Continuidade

Este projeto é de uso livre para fins educacionais e competitivos na FTC
A documentação é passada para cada geração da equipe, garantindo continuidade e qualidade

## Créditos

Equipe IRONHIDE #26956 - Decode 2025-2026