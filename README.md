# FTC TeamCode

Repositório contendo o **TeamCode** do robô FTC, incluindo OpModes, subsistemas e utilitários para controle do robô.

---

## Estrutura do Repositório

``` bash
TeamCode/
├── src/main/java/org/firstinspires/ftc/teamcode/
│ ├── opmodes/ # OpModes (TeleOp e Autonomous)
│ ├── subsystems/ # Classes de subsistemas (Intake, Shooter, TankDrive)
│ ├── util/ # Funções auxiliares (PID, sensores, etc.)
│ └── constants/ # Constantes do robô (KPIs, limites de motores, etc.)
├── build.gradle
└── README.md
```

---

## Sobre o Código

### OpModes
Os OpModes são classes que definem os modos de operação do robô:
- **TeleOp:** controles manuais do robô via Driver Station.
- **Autonomous:** sequências automáticas para partidas.

Cada OpMode interage com os subsistemas do robô para executar comandos de movimento, disparo, coleta de elementos, etc.

### Subsystems
Os subsistemas abstraem partes do robô, permitindo organizar o código:
- **TankDrive:** controle dos motores de locomoção.
- **Shooter:** controle do mecanismo de disparo.
- **Intake/Conveyor:** coleta e transporte de elementos.
- **Sensors:** leitura de sensores como pH, CO₂, giroscópio, distância, etc.

Exemplo de uso em um OpMode:
``` java
TankDrive drive = new TankDrive();
drive.init(hardwareMap);
drive.setPower(0.5, 0.5); // anda para frente
```

### Utilitários

- PIDFunctions: controle PID para motores e mecanismos.
- Timer e ElapsedTime: medição de tempo e delays.
- Funções auxiliares: cálculos de ângulo, ajustes de posição e leitura de sensores.

### Constants

Centraliza valores do robô, como limites de motor, posições de servo e parâmetros PID:
``` java
public class PIDConstants {
public static double Kp = 0.01;
public static double Ki = 0.001;
public static double Kd = 0.005;
}
```