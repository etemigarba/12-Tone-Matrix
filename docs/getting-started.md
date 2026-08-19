# Getting Started Guide

## Quick Start (30 Seconds)

### Windows (Batch Script)
```cmd
git clone https://github.com/etemigarba/12-Tone-Matrix.git
cd 12-Tone-Matrix
TwelveToneMatrix.bat
```

### Linux/macOS/Windows (Manual)
```bash
git clone https://github.com/etemigarba/12-Tone-Matrix.git
cd 12-Tone-Matrix
javac TwelveToneMatrix.java
java TwelveToneMatrix
```

---

## Detailed Installation

### 1. Verify Java Installation

```bash
java -version
javac -version
```

**Expected Output** (Java 8+):
```
java version "17.0.x" 2023-xx-xx
Java(TM) SE Runtime Environment (build 17.0.x+xx)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx, mixed mode)

javac 17.0.x
```

**If not installed**:
- **Windows**: Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [Eclipse Temurin](https://adoptium.net/)
- **Linux**: `sudo apt install openjdk-17-jdk` (Ubuntu/Debian) or `sudo dnf install java-17-openjdk-devel` (Fedora)
- **macOS**: `brew install openjdk@17`

### 2. Clone Repository

```bash
# HTTPS (recommended)
git clone https://github.com/etemigarba/12-Tone-Matrix.git

# SSH (if you have SSH keys configured)
git clone git@github.com:etemigarba/12-Tone-Matrix.git
```

### 3. Navigate to Project

```bash
cd 12-Tone-Matrix
```

### 4. Compile

```bash
# Standard compilation
javac TwelveToneMatrix.java

# With warnings (recommended)
javac -Xlint:deprecation -Xlint:unchecked TwelveToneMatrix.java
```

**Expected Output**:
```
TwelveToneMatrix.java:5: warning: [unchecked] unchecked call to ...
Note: TwelveToneMatrix.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
```

This is normal — the code uses raw `Object[]` arrays (legacy Java 2016 style).

### 5. Run

```bash
java TwelveToneMatrix
```

**Expected Output**:
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

Displaying the Pitch Notes: 
D C# A A# F D# E C G# G F# B 
A# B F# G C A A# F# D# D C# G 
E D# B C G# F F# E A# A G# C# 
D# D C C# G F F# D# A A# G C# 
G F# D D# A# G# A F C B A# D 
A G# E F C A# B G# D C# B D# 
G# G D# E B F# G C C# B A C 
C B G G# D C# D# C G# G F# A# 
E D# B C G# F F# E A# A G# C# 
F E C C# G# F# G D D# C B D# 
F# F C# D A A# B D# E D C D# 
C# C G# A D# C D# C# A# G# F
```

---

## Customizing the Tone Row

### Method 1: Edit Source Code (Permanent)

1. Open `TwelveToneMatrix.java` in a text editor
2. Locate line 13:
   ```java
   public Object originalRow[] = {"D","C#","A","A#","F","D#","E","C","G#","G","F#","B"};
   ```
3. Replace with your 12-note row (must use note names from `chromaticPitches`):
   ```java
   // Example: Berg's Lyric Suite
   public Object originalRow[] = {"F","E","C","A","G","D","B","A#","D#","C#","G#","F#"};
   ```
4. Save and recompile:
   ```bash
   javac TwelveToneMatrix.java
   java TwelveToneMatrix
   ```

### Method 2: Programmatic (Flexible)

Create a wrapper class:
```java
// MyMatrix.java
public class MyMatrix {
    public static void main(String[] args) {
        TwelveToneMatrix matrix = new TwelveToneMatrix();
        
        // Webern Op. 21
        matrix.originalRow = new Object[]{
            "C", "D", "F", "A#", "G#", "A", 
            "E", "G", "F#", "C#", "D#", "B"
        };
        
        // Trigger recomputation
        Object[] pc = matrix.getPichClassOriginalRow(
            matrix.chromaticPitches, 
            matrix.originalRow
        );
        Object[][] m = matrix.getTwelveToneMatrix(pc);
        matrix.displayPitchClassMatrix(m);
        matrix.displayPitchNotesMatrix(m);
    }
}
```

Compile and run:
```bash
javac MyMatrix.java
java MyMatrix
```

---

## Understanding the Output

### Pitch Class Matrix (Numbers 0–11)

Each row represents a **transposition** of the prime row.
Each column represents a **transposition** of the inversion.

```
Row 0 (P₀):  2 1 9 10 5 3 4 0 8 7 6 11  ← Your original row
Row 1 (P₁):  10 11 7 8 3 1 2 10 6 5 4 9  ← P₀ transposed by interval
...
```

**Reading the Matrix**:
- **Horizontal (rows)**: Prime forms P₀ through P₁₁
- **Vertical (columns)**: Inversion forms I₀ through I₁₁
- **Diagonal**: Interval relationships

### Pitch Notes Matrix (Note Names)

Same matrix, translated to musical notation:
```
Row 0: D C# A A# F D# E C G# G F# B
Row 1: A# B F# G C A A# F# D# D C# G
...
```

---

## Famous Tone Rows to Try

| Composer | Work | Tone Row |
|----------|------|----------|
| **Schoenberg** | Suite for Piano, Op. 25 | `E, F, G, D#, F#, C#, G#, A, C, B, D, A#` |
| **Webern** | Symphony, Op. 21 | `C, D, F, A#, G#, A, E, G, F#, C#, D#, B` |
| **Berg** | Violin Concerto | `G, B, D, F, A, C, E, G#, B, C#, D#, F#` |
| **Berg** | Lyric Suite | `F, E, C, A, G, D, B, A#, D#, C#, G#, F#` |
| **Stravinsky** | In Memoriam Dylan Thomas | `C, D#, E, G, A, B, C#, D, F, F#, G#, A#` |

**Format for Java**: Replace spaces with `","` and wrap in braces:
```java
{"E","F","G","D#","F#","C#","G#","A","C","B","D","A#"}
```

---

## Troubleshooting

### "javac: command not found"
**Cause**: JDK not installed or not in PATH
**Fix**: Install JDK and add `JAVA_HOME/bin` to PATH

### "Error: Could not find or load main class TwelveToneMatrix"
**Cause**: Running from wrong directory or class not compiled
**Fix**: 
```bash
cd /path/to/12-Tone-Matrix
javac TwelveToneMatrix.java
java TwelveToneMatrix
```

### "Unchecked or unsafe operations" warnings
**Cause**: Legacy `Object[]` usage (normal for this codebase)
**Fix**: Ignore — or refactor to use generics (see Architecture docs)

### Output doesn't match expected
**Cause**: Modified `originalRow` or `chromaticPitches`
**Fix**: Verify line 12-13 in `TwelveToneMatrix.java` match defaults

### Note names not recognized (e.g., "Db" instead of "C#")
**Cause**: `chromaticPitches` only contains sharp notation
**Fix**: Use sharp names only, or extend `chromaticPitches` array

---

## Development Workflow

### Making Changes

1. **Edit** `TwelveToneMatrix.java`
2. **Compile**: `javac TwelveToneMatrix.java`
3. **Test**: `java TwelveToneMatrix`
4. **Commit** (if contributing — see CONTRIBUTING.md)

### Using an IDE

**IntelliJ IDEA / Eclipse / VS Code**:
1. File → Open → Select `12-Tone-Matrix` folder
2. IDE detects Java project automatically
3. Run `TwelveToneMatrix.main()` via green play button

**No build configuration needed** — single file, no dependencies.

---

## Next Steps

- Read [Architecture Documentation](architecture.md) for technical details
- Read [API Reference](api-reference.md) for method signatures
- Explore [Archive](../Archive/) for historical versions
- See [Contributing Guidelines](../CONTRIBUTING.md) for contribution process

---

## Support

- **Documentation Issues**: Open a GitHub Issue
- **Algorithm Questions**: Refer to twelve-tone theory references in README
- **Permission Requests**: Contact Etemi Joshua Garba