# Board Game Planner

A visually unique, **full-stack board game management tool** with a hand-drawn sketch aesthetic. This project demonstrates advanced front-end data handling and a **graceful degradation** system from a Spring Boot backend to a static JSON architecture.

**[Live Demo](https://aprilohiy.github.io/BoardGamePlanner/)**

## Key Features
* **Hand-Drawn UI**: Custom CSS-based "sketch" interface without external component libraries.
* **Smart Sorting**: Real-time multi-dimensional sorting (Score, Rank, Complexity, Players, Time).
* **Hybrid Engine**: Automatically switches to a static `collection.json` mode when the Spring Boot backend is unavailable.
* **List Management**: Add games to a curated play list, export to `.txt`, and remove items via double-click.

## Tech Stack
* **Frontend**: HTML5, Tailwind CSS, JavaScript (ES6+).
* **Backend**: Java 17, Spring Boot, Maven.
* **Data**: Python scripts for cleaning BoardGameGeek (BGG) CSV data.

## Setup

### Local (Full-Stack)
1. Ensure Java 17 is installed.
2. Run the backend: `cd backend && mvn spring-boot:run`.
3. Open `index.html` in your browser.

### Static Mode
Simply open `index.html` or visit the GitHub Pages link to run the app using the local JSON data.

## Usage
* **Search**: Filter games by name using the search bar.
* **Pick**: Click "Pick!" to add a game to your list.
* **Remove**: Double-click any item in the side panel to delete it.
* **Export**: Save your selection as a text file.
