**MIEIC, 2ºA LPOO – 2020/21 FEUP**

## LPOO_33 - BOMBERMAN:

## Bomberman Game

O projeto por nós realizado é baseado no jogo Bomberman, e apresenta três modos independentes : Singleplayer, Multiplayer e BoardBuilder.
O objetivo em modo single será com o "Bomber" derrotar os monstros que aparecerão no mapa com as bombas plantadas pelo utilizador,
sem morrer nem para as pŕoprias bombas nem para os monstros. Em modo multiplayer dois jogadores irão se enfrentar utilizando as bombas. 
No modo BoardBuilder, o utilizador tem a opção de criar os mapas no qual irá jogar.

```
Trabalho realizado por Bruno Gomes (up201906401) , Miguel Amorim (up201907756) e Tomás Fidalgo (up201906743)
```
## Features Implementadas

- Movimento do Bomber - A personagem movimenta-se ao longo do mapa, com a utilização das setas do teclado.
- Plantar uma bomba - A personagem de jogo irá plantar uma bomba quando pressionar a SpaceBar.
- Obter Coins - A personagem de jogo poderá obter moedas para melhorar as suas bombas, destruindo paredes do mapa.
- Morrer para os monstros - O jogo acaba se a personagem se encontrar com um dos monstros.
- Menu das opções de jogo - Menu inicial com as opções de jogo.
- Melhoramento com Coins - Apanhar Coins melhora as bombas, a velocidade da personagem ou o range da bomba. 
- Ter três vidas - A personagem apresentar três vidas.
- Matar monstros - É possível matar os monstros se a bomba ao explodir os atingir.
- O modo multiplayer - Modo de jogar duas pessoas simultaneâmente
- Criação de mapas - No modo BoardBuilder, o utilizador poderá criar mapas à sua escolha para futura utilização.
- Controlo do movimento dos monstros - Movimentos controlado dos monstros por nível


![image](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/ScreenShots/screenshot2.png?raw=true "Title")

![image](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/ScreenShots/screenshot5.png?raw=true "Title")

![image](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/ScreenShots/screenshot6.png?raw=true "Title")

![image](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/ScreenShots/screenshot7.png?raw=true "Title")

### Gifs

![](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/gifs/gif1.gif)

![](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/gifs/gif2.gif)

## Future Features
- Desbloquear passagem para o nível seguinte - Quando o Bomber derrotar todos os monstros desse nível é desbloqueada uma passagem para o nível seguinte.
Esta feature não foi implementada, o nível seguinte é imediatamente lançado quando o anterior acaba.


## Design

### Problema encontrado

Visto o nosso projeto ser um jogo, e nós querermos processar input, dar update do estado do jogo e renderizá-lo continuamente, em tempo real, só faz sentido utilizarmos o GameLoop como design pattern.

### Implementação

Foi utilizado um ciclo while() onde todo o jogo ocorre onde ocorrem os seguintes processos:
-Verificação de input e processamento do mesmo;
-Update de objetos presentes no jogo;
-Renderização do display

### Consequências

Foi possível por o jogo a correr, continuamente e em tempo real, como era pretendido. A divisão do código por partes, relativas à sua importância no decorrer do jogo proporcionou também uma melhor organização do projeto.


## Design

### Problema encontrado

O jogo tem uma forte componente visual, devida à utilização do Lanterna. Com isto, o design pattern MVC seria o mais adequado, estando a parte View relacionada com esta componente.
 
### Implementação

Com este pattern como estrutura, o código foi dividido em três partes, estando , como já foi referido, a parte view relacionada com o Lanterna e a componente visual, a parte Model relacionada com toda a informação armazenada e a parte Controller com o controlo da informação e com o utilizador e as suas interações. 

### Consequências

Foi possível alcançar o objetivo de organizar de forma eficiente o código exigindo menos manutenção do código.

![image](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/ScreenShots/screenshot3.png?raw=true "Title")

## Testes

Foram também implementados alguns testes que nos permitiram testar algumas classes e os seus métodos.
Estes testes tentaram sempre ser implementados isoladamente de outras classes, utilizando mocks sempre que necesário.
Algumas das classes, como por exemplo as relacionadas com a parte visual não conseguiram ser testadas.
Desta forma, foram testadas quase todas as classes pertencentes à parte Model parte das pertencentes à classe controller, segundo o design pattern MVC.

### Coverage
Os testes com coverage refletem o nosso trabalho, sendo que 60% das classes foram testadas,assim como 44% dos seus métodos.

![image](https://github.com/FEUP-LPOO-2021/lpoo-2021-g33/blob/master/docs/ScreenShots/coverage.jpg?raw=true "Title")


##Code Smells

###Solved Code Smells
- Utilização de Macros: solução para a repetição desnecessária de valores (Ex: EntityToken)
- Dead Code: remoção de código não utilizado

###Unsolved Code Smells
- Métodos demasiado longos (Ex: MenuController.ShowModes(), poderia ter sido resolvido com a sua distribuição por mais métodos);



## Entrega do Trabalho

O trabalho foi realizado igualmente pelos três membros do grupo.
- Bruno Gomes - 33%
- Miguel Amorim - 33%
- Tomás Fidalgo - 33%


