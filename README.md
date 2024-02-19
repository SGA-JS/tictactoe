# tictactoe

In this exercise, you are required to extend the Tic-Tac-Toe application so that the user has the option to play with the computer OR with another player on the same device: 
(1) When the app starts, the app displays two choices: Play with the Computer OR Play with another player. The user then makes his/her choice and the game starts on a second Activity. 
(2) When the user plays with the computer, the computer is not required to find the optimal play that maximises the computer chances of win. It only needs to pick a random empty cell. 

The application should have the following properties 
(1) The state of the application is preserved when you rotate the device (switch between Portrait and Landscape orientations). 
	(2) The size of the grid changes from 3x3 to 5x5 when you switch from portrait to landscape and back to 3x3 when you switch back to portrait. Importantly: a. When you switch from portrait to landscape, you should preserve the status of the game. 
	b. When you switch from landscape to portrait, the game starts over. 
	

For this exercise, you can download the Tic-Tac-Toe project from the LMS website (see the Lab session of Week 3) and extend it by implementing the functionalities above. 
You are required to provide: 
(1) A documentation that explains the Model-View-Controller of the entire application and the class diagrams (similar to what we have seen in Tutorial 1). 
(2) The source code of the entire application. 
(3) Sample screen shots explaining all major operations asked. 

Marking guide for Question 4: 
	- Having two activities (20 pts) o The start Activity let the user choose whether to play with the computer (mode 1) or with a second player (mode 2) 
	o The second Activity will host the game. It should be the same activity for the two modes of the game 
	
