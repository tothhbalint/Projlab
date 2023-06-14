# Participants
- Buzás Gergő [@gergobuzas](https://github.com/gergobuzas)
- Moór Bence Dániel [@mrbncdnl](https://github.com/mrbncdnl)
- Tóth Bálint [@tothhbalint](https://github.com/tothhbalint)
- Mike Mátyás [@Majkszon](https://github.com/Majkszon)
- Márki Ádám [@marki-a](https://github.com/marki-a)
# About
This project was created in a team for a project subject we had in university. We had to plan and architect solutions for the provided problem at each stage of the project before we could start writing any code.
- The stages:
  - Skeleton
    - The skeleton of the project with a simple terminal communication where the Flow Charts could be tested.  
  - Proto
    - Here the basic functions of the model had to be implemented. You could interact with it through terminal commands.
  - Graphical
    - The final product, the model was completed with a View and Controller for the project, in respect of MVC principles. For this we used Java Swing   
# Task
Pipes in the desert

In a desert a complex pipe system transfers water from the mountain springs to the cisterns of the cities beyond the desert. The pipe system is built from simple pipes and from active elements (spring, cistern, solar water pump, etc.) connected to the pipes. Multiple pipes can be connected to a pump, but the number of connectable pipes is specific to each pump. For each pump, the incoming and the outgoing pipe can be individually selected, but a pump can have only a single input and a single output. The other pipes connected to the pump are closed. At random times, a pump may go out of order, and in this case, the water will not flow through the pump. Each pump contains a water tank, which is used as a temporary reservoir when the pump transfers water from the incoming pipe to the outgoing pipe. The pump can transfer water only if the outgoing pipe has some free capacity.

The pipe system can be extended and can be changed. The pipes are flexible enough so that one of their ends can be disconnected from an active element and can be connected to another. New pipes are manufactured regularly at the cisterns. One of their ends is connected to a cistern the other end is free. When a pipe has a free end, water transferred into the pipe flows out to the desert.

The pipe system is maintained by plumbers. They can fix broken pumps, they can set the directions of the pumps, and they can fix leaking pipes, too. When a pipe is leaking, the water will spill out, and no water reaches the end of the pipe. The plumbers’ task is to transfer as much water as possible from the springs to the cisterns. They can extend the system with the pipes manufactured at the cisterns and they can also pick up new pumps also manufactured at the cisterns. A new pump can be inserted into the middle of a pipe by cutting the pipe in half and connecting the free ends to the pump.

There are also saboteurs living on the pipe system. They can change the directions of the pumps, and they can puncture pipes.

Since the desert is a dangerous place, the plumbers and saboteurs can only move on the pipes and the pumps. People can go around each other at the pumps, but they cannot go around each other on pipes: only a single person can go along a pipe.

The game is played by two teams. Each team has at least two players. The saboteur team’s task is to leak as much water as possible from the pipe system, the plumber team’s task is to transfer as much water as possible to the cisterns. The game is won by the team collecting the most amount of water.

# Screenshots
Start page  
![image](https://github.com/tothhbalint/Projlab/assets/27715445/af85815d-6a5d-4686-9cab-5f5bffda15f2)

Main game  
![image](https://github.com/tothhbalint/Projlab/assets/27715445/99fb4f6c-372f-44f1-ac65-b5c1a1eeae1f)


# Run 
The project was precompiled into a jar file, so it's easier to run it. To do so you have to run the following code in the terminal:
```
java App.jar
```
