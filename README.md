# Game Of Life

Java implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

### Motivation

I discovered Conway's Game of Life while studying computer engineering at the 
University of Seville. Since that day, I found myself engrossed by the idea of 
a world in which mathematics decides who perishes and who survives.

For years, I had plans to implement the game from scratch, but time passed and 
many things happened in my life. I graduated from university, became a researcher, 
quit, got a job as a software engineer and even adopted a cat. I could not find 
the right moment to start working on this project.

However, everything changed when [@Sumlarys](https://www.github.com/Sumlarys), 
a good friend of mine, started studying programming at trade school. Suddenly 
we found ourselves talking about code almost every day and I thought that this 
project could be a good learning opportunity for both of us.

> **_NOTE:_** Due to its educational nature, this project is not intended to 
> represent a production-ready solution. It is a demonstration of various design 
> patterns, advanced features, and coding techniques. While the code may illustrate 
> good programming practices, it may not adhere to all the best practices necessary 
> for a real-world, production-grade application.

### Goals

* Implement Conway's Game of Life using only Java
* Prioritize the Java library over third-party libraries as much as possible
* Preprocess neighbors for better efficiency
* Provide a builder to construct game controller instances programmatically
* Create a GUI to play the game interactively

### Requirements

Java 17 or newer

### Usage

#### Start the game with the GUI (recommended)

Create an instance of the game graphical user interface ([GUI](src/main/java/com/github/pedbertor/gol/gui/GUI.java)):

```java
GUI.create();
```

#### Start the game with Controller

Alternatively, it is possible to use [ControllerBuilder](src/main/java/com/github/pedbertor/gol/controller/ControllerBuilder.java)
to create an instance of [Controller](src/main/java/com/github/pedbertor/gol/controller/Controller.java) with various configuration 
options, set the initial generation of alive cells, and start the game by calling `start`:

```java
// Create a game controller with a 5x10 grid and 1 second game loop duration
Controller controller = new ControllerBuilder()
        .setGridHeight(5)
        .setGridWidth(10)
        .setGameLoopDuration(1000L)
        .create();

// Set initial pattern (blinker oscillator)
controller.switchCellState(2, 3);
controller.switchCellState(2, 4);
controller.switchCellState(2, 5);

// Start the game
controller.start();
```

### Collaborators

* [@Sumlarys](https://www.github.com/Sumlarys)

### License

This project is released under the [MIT License](LICENSE).

```
Copyright (c) 2024 Pedro Bernaldez and others

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
