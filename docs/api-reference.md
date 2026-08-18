# API Reference

## Package: `default` (unnamed package)

### Class: `TwelveToneMatrix`

**Description**: Main class implementing the twelve-tone matrix algorithm for serial music composition.

**Since**: 2016

**Author**: Ethereal Multimedia Technology R&D

---

## Fields

### `chromaticPitches`

```java
public Object chromaticPitches[] = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
```

**Description**: Lookup array mapping pitch class integers (0–11) to standard note names using sharp notation.

**Type**: `Object[12]`

**Modifiability**: Public — can be reassigned before instantiation.

**Note Names**:
| Index | Note | Pitch Class |
|-------|------|-------------|
| 0 | C | 0 |
| 1 | C# | 1 |
| 2 | D | 2 |
| 3 | D# | 3 |
| 4 | E | 4 |
| 5 | F | 5 |
| 6 | F# | 6 |
| 7 | G | 7 |
| 8 | G# | 8 |
| 9 | A | 9 |
| 10 | A# | 10 |
| 11 | B | 11 |

---

### `originalRow`

```java
public Object originalRow[] = {"D","C#","A","A#","F","D#","E","C","G#","G","F#","B"};
```

**Description**: The user-defined 12-tone row (prime form, P₀). Must contain all 12 pitch classes exactly once.

**Type**: `Object[12]`

**Modifiability**: Public — modify before instantiation to use a custom tone row.

**Default Row Analysis**:
| Position | Note | Pitch Class |
|----------|------|-------------|
| 0 | D | 2 |
| 1 | C# | 1 |
| 2 | A | 9 |
| 3 | A# | 10 |
| 4 | F | 5 |
| 5 | D# | 3 |
| 6 | E | 4 |
| 7 | C | 0 |
| 8 | G# | 8 |
| 9 | G | 7 |
| 10 | F# | 6 |
| 11 | B | 11 |

**Validation Required**: Caller must ensure:
- Exactly 12 elements
- All elements exist in `chromaticPitches`
- No duplicate pitch classes

---

## Constructors

### `TwelveToneMatrix()`

```java
public TwelveToneMatrix()
```

**Description**: Default constructor. Immediately computes and displays the matrix for the current `originalRow`.

**Behavior**:
1. Converts `originalRow` to pitch classes via `getPichClassOriginalRow()`
2. Generates 12×12 matrix via `getTwelveToneMatrix()`
3. Displays pitch class matrix via `displayPitchClassMatrix()`
4. Displays pitch note matrix via `displayPitchNotesMatrix()`

**Side Effects**: Prints to `System.out`

**Example**:
```java
// Uses default originalRow
new TwelveToneMatrix();

// Custom row
TwelveToneMatrix matrix = new TwelveToneMatrix();
matrix.originalRow = new Object[]{"C", "D", "E", "F#", "G#", "A#", "B", "A", "G", "F", "D#", "C#"};
new TwelveToneMatrix(); // Uses modified row
```

---

## Methods

### `getPichClassOriginalRow`

```java
public Object[] getPichClassOriginalRow(Object[] chromaticPitches, Object[] originalRow)
```

**Description**: Converts an array of note names to their corresponding pitch class integers (0–11).

**Parameters**:
| Parameter | Type | Description |
|-----------|------|-------------|
| `chromaticPitches` | `Object[]` | Lookup array of 12 note names in chromatic order |
| `originalRow` | `Object[]` | 12-element array of note names to convert |

**Returns**: `Object[12]` — Array of pitch class integers (as `Object` wrappers)

**Algorithm**:
```java
for i = 0 to 11:
    for j = 0 to 11:
        if originalRow[i] == chromaticPitches[j]:
            pitchClass[i] = j
            break
```

**Complexity**: O(144) = O(1) constant time

**Exceptions**: Returns `null` elements for unmatched notes (no exception thrown)

**Example**:
```java
Object[] chromatic = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
Object[] row = {"C", "E", "G", "B", "D", "F#", "A", "C#", "F", "G#", "D#", "A#"};
Object[] pitchClasses = matrix.getPichClassOriginalRow(chromatic, row);
// Result: [0, 4, 7, 11, 2, 6, 9, 1, 5, 8, 3, 10]
```

---

### `getTwelveToneMatrix`

```java
public Object[][] getTwelveToneMatrix(Object[] pitchClassRow)
```

**Description**: Generates the complete 12×12 twelve-tone matrix from a pitch class row.

**Parameters**:
| Parameter | Type | Description |
|-----------|------|-------------|
| `pitchClassRow` | `Object[]` | 12-element array of pitch class integers (0–11) |

**Returns**: `Object[12][12]` — The complete matrix where:
- `matrix[0][*]` = Prime forms (P₀–P₁₁)
- `matrix[*][0]` = Inversion forms (I₀–I₁₁)
- `matrix[i][j]` = Transposition of P₀ by interval (matrix[i][0] + matrix[0][j]) mod 12

**Algorithm**:
```java
// Row 0: Prime
matrix[0][j] = pitchClassRow[j]

// Column 0: Inversion
for i = 1 to 11:
    value = 12 - matrix[0][i]
    matrix[i][0] = (value >= 12) ? value % 12 : value

// Remaining: Transposition
for i = 1 to 11:
    for j = 1 to 11:
        value = matrix[i][0] + matrix[0][j]
        matrix[i][j] = (value >= 12) ? value % 12 : value
```

**Mathematical Properties**:
- Each row is a transposition of P₀
- Each column is a transposition of I₀
- Matrix[i][j] = (Iᵢ + Pⱼ) mod 12
- All 12 pitch classes appear exactly once in each row and column (Latin square)

**Complexity**: O(144) = O(1) constant time

**Example**:
```java
Object[] prime = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // Chromatic scale
Object[][] matrix = matrix.getTwelveToneMatrix(prime);
// matrix[0] = [0,1,2,3,4,5,6,7,8,9,10,11] (P0)
// matrix[1][0] = 11 (I1)
// matrix[1] = [11,0,1,2,3,4,5,6,7,8,9,10] (P11)
```

---

### `displayPitchClassMatrix`

```java
public void displayPitchClassMatrix(Object[][] matrix)
```

**Description**: Prints the matrix as pitch class integers (0–11) to `System.out`.

**Parameters**:
| Parameter | Type | Description |
|-----------|------|-------------|
| `matrix` | `Object[][]` | 12×12 matrix from `getTwelveToneMatrix()` |

**Output Format**:
```
Displaying the Pitch Class: 
2 1 9 10 5 3 4 0 8 7 6 11 
10 11 7 8 3 1 2 10 6 5 4 9 
... (12 rows)
```

**Side Effects**: Writes to `System.out`

**Example**:
```java
Object[][] matrix = getTwelveToneMatrix(pitchClasses);
displayPitchClassMatrix(matrix);
```

---

### `displayPitchNotesMatrix`

```java
public void displayPitchNotesMatrix(Object[][] matrix)
```

**Description**: Prints the matrix as note names (using `chromaticPitches` lookup) to `System.out`.

**Parameters**:
| Parameter | Type | Description |
|-----------|------|-------------|
| `matrix` | `Object[][]` | 12×12 matrix from `getTwelveToneMatrix()` |

**Output Format**:
```
Displaying the Pitch Notes: 
D C# A A# F D# E C G# G F# B 
A# B F# G C A A# F# D# D C# G 
... (12 rows)
```

**Algorithm**:
```java
for each cell matrix[i][j]:
    pitchClass = Integer.parseInt(matrix[i][j].toString())
    noteName = chromaticPitches[pitchClass]
    print noteName
```

**Side Effects**: Writes to `System.out`

**Dependencies**: Requires `chromaticPitches` field to be accessible (instance field)

---

## Main Method

### `main`

```java
public static void main(String args[])
```

**Description**: Program entry point. Instantiates `TwelveToneMatrix`, triggering automatic matrix computation and display.

**Parameters**: `args` — Command-line arguments (currently unused)

**Behavior**:
```java
public static void main(String args[]) {
    new TwelveToneMatrix();
}
```

**Exit Code**: Always 0 (no error handling)

---

## Usage Patterns

### Pattern 1: Default Execution (Simplest)

```java
public class Main {
    public static void main(String[] args) {
        new TwelveToneMatrix(); // Uses default originalRow
    }
}
```

### Pattern 2: Custom Tone Row

```java
public class Main {
    public static void main(String[] args) {
        TwelveToneMatrix matrix = new TwelveToneMatrix();
        
        // Berg's Lyric Suite row
        matrix.originalRow = new Object[]{
            "F", "E", "C", "A", "G", "D", 
            "B", "A#", "D#", "C#", "G#", "F#"
        };
        
        // Re-run with new row (create new instance)
        new TwelveToneMatrix();
    }
}
```

### Pattern 3: Programmatic Access (No Auto-Display)

```java
public class Analysis {
    public static void main(String[] args) {
        TwelveToneMatrix matrix = new TwelveToneMatrix();
        
        // Get pitch classes
        Object[] pitchClasses = matrix.getPichClassOriginalRow(
            matrix.chromaticPitches, 
            matrix.originalRow
        );
        
        // Generate matrix
        Object[][] twelveToneMatrix = matrix.getTwelveToneMatrix(pitchClasses);
        
        // Custom analysis
        analyzeMatrix(twelveToneMatrix);
        
        // Optional: display
        matrix.displayPitchClassMatrix(twelveToneMatrix);
    }
    
    static void analyzeMatrix(Object[][] m) {
        // Check combinatoriality, invariance, etc.
    }
}
```

### Pattern 4: Batch Processing Multiple Rows

```java
public class BatchAnalysis {
    public static void main(String[] args) {
        TwelveToneMatrix matrix = new TwelveToneMatrix();
        Object[][] rows = {
            {"C", "D", "E", "F#", "G#", "A#", "B", "A", "G", "F", "D#", "C#"},
            {"F", "E", "C", "A", "G", "D", "B", "A#", "D#", "C#", "G#", "F#"},
            // ... more rows
        };
        
        for (Object[] row : rows) {
            Object[] pc = matrix.getPichClassOriginalRow(matrix.chromaticPitches, row);
            Object[][] m = matrix.getTwelveToneMatrix(pc);
            // Process m...
        }
    }
}
```

---

## Matrix Forms Reference

Given the generated 12×12 matrix `M`:

| Form | Notation | Matrix Access | Description |
|------|----------|---------------|-------------|
| **Prime** | P₀–P₁₁ | `M[0][*]` to `M[11][*]` | Rows 0–11, left-to-right |
| **Inversion** | I₀–I₁₁ | `M[*][0]` to `M[*][11]` | Columns 0–11, top-to-bottom |
| **Retrograde** | R₀–R₁₁ | `M[0][11..0]` to `M[11][11..0]` | Rows 0–11, right-to-left |
| **Retrograde Inversion** | RI₀–RI₁₁ | `M[11..0][0]` to `M[11..0][11]` | Columns 0–11, bottom-to-top |

**Transposition Relationship**:
- Pₜ = P₀ transposed by t semitones
- Iₜ = I₀ transposed by t semitones
- Rₜ = Retrograde of Pₜ
- RIₜ = Retrograde of Iₜ

---

## Error Handling

**Current State**: Minimal — no exceptions thrown, no validation.

**Potential Issues**:
| Scenario | Current Behavior | Recommended Fix |
|----------|------------------|-----------------|
| Duplicate notes in row | Silent incorrect matrix | Throw `IllegalArgumentException` |
| Invalid note name | `null` in pitchClass array | Validate input, throw exception |
| Wrong array length | `ArrayIndexOutOfBoundsException` | Validate length == 12 |
| Null input | `NullPointerException` | Null checks with descriptive messages |

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2016-04-08 | Initial release (TwelveToneMatrix.java) |
| 1.1 | 2016-03-26 to 2016-04-08 | Iterative development (Archive versions) |

See [CHANGELOG.md](../CHANGELOG.md) for details.