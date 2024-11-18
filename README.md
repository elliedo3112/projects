
# Spelling Bee and Wordle Game Application

## Overview

This repository contains the code for two interactive word games—**Spelling Bee** and **Wordle**—developed using **Java Swing**. The project utilizes the **Model-View-Controller (MVC) architecture** to ensure scalability, modularity, and efficient gameplay. The application provides an engaging and responsive user experience with real-time feedback and scoring.

## Features

### Spelling Bee
- Players are given a set of letters and must form as many words as possible, with one central letter being mandatory for every word.
- Scoring is based on the length and complexity of the words formed.

### Wordle
- Players guess a hidden five-letter word within a limited number of attempts.
- Feedback after each guess shows which letters are correct (correctly placed, correct but misplaced, or incorrect).

## Technologies
- **Java Swing**: For creating interactive GUI components.
- **MVC Architecture**: Separates game logic (Model), user interface (View), and interaction (Controller).
- **Data Structures**: HashSets and HashMaps are used to store and validate words efficiently.
- **UML Diagrams**: Used for system design and scalability planning.

## Getting Started

### Prerequisites
- Java JDK 8 or later
- IDE (e.g., IntelliJ IDEA, Eclipse) or command line

### Installation
1. Clone this repository:
    ```bash
    git clone https://github.com/your-username/spelling-bee-wordle.git
    ```
2. Open the project in your IDE or navigate to the project directory in the terminal.
3. Compile and run the Java program.

### Running the Application
- **Spelling Bee Game**: Start the game and try to form as many words as possible from a given set of letters.
- **Wordle Game**: Start the game and guess the five-letter word within a set number of attempts.

## Architecture

This project is designed using the **Model-View-Controller (MVC) pattern**, ensuring the separation of concerns and ease of maintenance.

- **Model**: Contains the game logic (word validation, scoring, and game state).
- **View**: Handles the graphical interface (Java Swing components like buttons, labels, and grids).
- **Controller**: Acts as an intermediary between the Model and the View, handling user interactions and updating the game state.

### UML Diagrams
- UML diagrams have been created to outline the structure of the system for future scalability and potential game expansions.

## Testing

- Extensive **unit tests** have been created to verify the functionality of word validation and game logic.
- **User acceptance testing** was conducted to refine the UI and gameplay mechanics.

## Challenges

- **Responsive Design**: Ensured the user interface scaled well on different screen sizes using Java Swing layout managers.
- **Word Validation**: Optimized dictionary lookups for fast and efficient word validation.
- **User Experience**: Iterated on feedback mechanisms to maintain game challenge while providing helpful hints.

## Key Learnings

- Implementing **MVC architecture** for modular development and separation of concerns.
- Enhancing **UI/UX design skills** to create intuitive and visually appealing interactive components.
- Strengthening algorithmic problem-solving for efficient word validation and feedback mechanisms.
- Gaining experience with **iterative development**, integrating feedback into successive project stages.
- Improving skills in **unit testing** and debugging to ensure the robustness of the game logic.
