# 12 Tone Matrix

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()
[![Year](https://img.shields.io/badge/Year-2016-blue.svg)]()

A Java implementation of the **Twelve-Tone Matrix** (also known as the *Babbitt Square* or *Twelve-Tone Row Matrix*), a fundamental tool in **serialism** and **twelve-tone technique** developed by Arnold Schoenberg. This application computes and displays the complete 12×12 matrix derived from a given tone row, showing all four forms: **Prime (P)**, **Inversion (I)**, **Retrograde (R)**, and **Retrograde Inversion (RI)**.

## Overview

The Twelve-Tone Matrix is a mathematical and musical construct used in atonal and serial composition. Given a 12-tone row (a specific ordering of the 12 pitch classes), the matrix generates all 48 row forms (12 transpositions × 4 transformations) used in twelve-tone composition.

This implementation:
- Accepts a user-defined 12-tone row (default: `D, C#, A, A#, F, D#, E, C, G#, G, F#, B`)
- Converts pitch names to pitch class integers (0–11)
- Computes the complete 12×12 matrix using modular arithmetic (mod 12)
- Displays both **pitch class numbers** (0–11) and **pitch note names** (C, C#, D, etc.)

### Musical Theory Background

In twelve-tone technique, the matrix represents:
- **Rows 0–11 (Prime forms)**: Transpositions of the original row
- **Columns 0–11 (Inversion forms)**: Transpositions of the inverted row
- **Retrograde**: Each row read right-to-left
- **Retrograde Inversion**: Each column read bottom-to-top

## Prerequisites

| Requirement | Version | Notes |
|-------------|---------|-------|
| **Java JDK** | 8 or higher | Tested on JDK 8, 11, 17, 21 |
| **Operating System** | Windows / Linux / macOS | Cross-platform |

No external dependencies or build tools (Maven, Gradle) required — pure Java standard library.

## Installation

### Option 1: Clone and Compile Manually

```bash
# Clone the repository
git clone https://github.com/etemigarba/12-Tone-Matrix.git
cd 12-Tone-Matrix

# Compile
javac TwelveToneMatrix.java

# Run
java TwelveToneMatrix
```

### Option 2: Using the Batch Script (Windows)

```cmd
# Double-click TwelveToneMatrix.bat or run from cmd:
TwelveToneMatrix.bat
```

The batch script compiles with deprecation and unchecked warnings enabled, then runs the application.

## Usage

### Default Execution

Running the program without modification uses the built-in tone row:

```java
public Object originalRow[] = {"D","C#","A","A#","F","D#","E","C","G#","G","F#","B"};
```

Output:
```
Displaying the Pitch Class: 
2 1 9 10 5 3 4 0 8 7 6 11 
10 11 7 8 3 1 2 10 6 5 4 9 
... (12 rows total)

Displaying the Pitch Notes: 
D C# A A# F D# E C G# G F# B 
A# B F# G C A A# F# D# D C# G 
... (12 rows total)
```

### Customizing the Tone Row

Edit `TwelveToneMatrix.java` and modify the `originalRow` array (line 13):

```java
// Example: Alban Berg's Lyric Suite row
public Object originalRow[] = {"F", "E", "C", "A", "G", "D", "B", "A#", "D#", "C#", "G#", "F#"};

// Example: Webern's Symphony Op. 21 row
public Object originalRow[] = {"C", "D", "F", "A#", "G#", "A", "E", "G", "F#", "C#", "D#", "B"};
```

Then recompile and run.

### Programmatic Usage

```java
TwelveToneMatrix matrix = new TwelveToneMatrix();
// Access methods directly:
Object[] pitchClasses = matrix.getPichClassOriginalRow(chromaticPitches, originalRow);
Object[][] twelveToneMatrix = matrix.getTwelveToneMatrix(pitchClasses);
matrix.displayPitchClassMatrix(twelveToneMatrix);
matrix.displayPitchNotesMatrix(twelveToneMatrix);
```

## Project Structure

```
12-Tone-Matrix/
├── TwelveToneMatrix.java      # Main source code
├── TwelveToneMatrix.class     # Compiled bytecode (generated)
├── TwelveToneMatrix.bat       # Windows build/run script
├── Archive/                   # Historical versions
│   ├── TwelveToneMatrix - Copy.java
│   ├── TwelveToneMatrix - Copy (2).java
│   ├── TwelveToneMatrix - Copy (3).java
│   └── TwelveToneMatrix - Copy (4).java
├── LICENSE                    # Proprietary license
├── CONTRIBUTING.md            # Contribution guidelines
├── CHANGELOG.md               # Version history
├── DEPENDENCIES.md            # Dependency documentation
├── CODEOWNERS                 # Code ownership
├── .gitignore                 # Git ignore rules
└── docs/
    ├── architecture.md        # Technical architecture
    ├── api-reference.md       # API documentation
    └── getting-started.md     # Detailed setup guide
```

## Key Classes and Methods

| Method | Description |
|--------|-------------|
| `getPichClassOriginalRow(chromaticPitches, originalRow)` | Converts note names to pitch class integers (0–11) |
| `getTwelveToneMatrix(pitchClassRow)` | Generates the 12×12 matrix using modular arithmetic |
| `displayPitchClassMatrix(matrix)` | Prints matrix as pitch class numbers (0–11) |
| `displayPitchNotesMatrix(matrix)` | Prints matrix as note names (C, C#, D, ...) |

## Algorithm

The matrix construction follows standard twelve-tone theory:

1. **Row 0 (Prime)**: The original pitch class row
2. **Column 0 (Inversion)**: `12 - Row[0][i] (mod 12)` for each i
3. **Remaining cells**: `Matrix[i][j] = (Matrix[i][0] + Matrix[0][j]) mod 12`

This ensures each row is a transposition of the prime form, and each column is a transposition of the inversion.

## Example Output (Pitch Classes)

```
Displaying the Pitch Class: 
2 1 9 10 5 3 4 0 8 7 6 11 
10 11 7 8 3 1 2 10 6 5 4 9 
4 3 11 0 7 5 6 4 0 11 10 1 
3 2 10 11 6 4 5 3 11 10 9 0 
7 6 2 3 10 8 9 7 3 2 1 4 
9 8 4 5 0 10 11 9 5 4 3 6 
8 7 3 4 11 9 10 8 4 3 2 5 
0 11 7 8 3 1 2 0 8 7 6 11 
4 3 11 0 7 5 6 4 0 11 10 1 
5 4 0 1 8 6 7 5 1 0 11 2 
6 5 1 2 9 7 8 6 2 1 0 3 
1 0 8 9 4 2 3 1 9 8 7 10 
```

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines.

> **⚠️ Copyright Notice**: This project is the intellectual property of **Etemi Joshua Garba**. Explicit written permission is required for any adoption, editing, refactoring, or redistribution of this codebase.

## License

This project is licensed under a **Proprietary License** — see the [LICENSE](LICENSE) file for details.

```
Copyright © 2016 Etemi Joshua Garba. All rights reserved.
Explicit permission required for adoption, editing, and refactoring.
```

## Support

- **Issues**: [GitHub Issues](https://github.com/etemigarba/12-Tone-Matrix/issues)
- **Owner**: Etemi Joshua Garba
- **Repository**: https://github.com/etemigarba/12-Tone-Matrix

## References

- Schoenberg, A. (1923). *Suite for Piano, Op. 25* — First published twelve-tone work
- Babbitt, M. (1955). *Twelve-Tone Rhythmic Structure* — Matrix formalization
- Whittall, A. (2008). *The Cambridge Introduction to Serialism* — Comprehensive theory reference