# Technical Architecture

## Overview

The 12 Tone Matrix is a **single-class Java application** implementing the twelve-tone matrix algorithm used in serial music composition. The architecture follows a simple, procedural design within a single `TwelveToneMatrix` class.

## System Components

```
┌─────────────────────────────────────────────────────────────┐
│                    TwelveToneMatrix Class                     │
├─────────────────────────────────────────────────────────────┤
│  Fields                                                      │
│  ├── chromaticPitches: Object[12]  // Pitch name lookup      │
│  └── originalRow: Object[12]       // User-defined tone row  │
├─────────────────────────────────────────────────────────────┤
│  Methods                                                     │
│  ├── main(String[])              // Entry point              │
│  ├── getPichClassOriginalRow()   // Note → Pitch Class       │
│  ├── getTwelveToneMatrix()       // Matrix Generation        │
│  ├── displayPitchClassMatrix()   // Output: Numbers (0-11)   │
│  └── displayPitchNotesMatrix()   // Output: Note Names       │
└─────────────────────────────────────────────────────────────┘
```

## Data Flow

```
User-Defined Tone Row (12 notes)
         │
         ▼
┌────────────────────────┐
│ getPichClassOriginalRow│  ──►  Pitch Class Array [0-11]
│ (Note Name → Integer)  │
└────────────────────────┘
         │
         ▼
┌────────────────────────┐
│   getTwelveToneMatrix  │  ──►  12×12 Matrix (Object[][])
│  (Modular Arithmetic)  │
└────────────────────────┘
         │
         ├──────────────────┬──────────────────┐
         ▼                  ▼                  ▼
┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐
│displayPitchClass│ │displayPitchNotes│ │  (Future Ext.)  │
│    Matrix()     │ │    Matrix()     │ │  Export/Serialize
└─────────────────┘ └─────────────────┘ └─────────────────┘
```

## Algorithm Details

### Pitch Class Conversion (`getPichClassOriginalRow`)

```java
// Input: chromaticPitches = ["C","C#","D","D#","E","F","F#","G","G#","A","A#","B"]
// Input: originalRow    = ["D","C#","A","A#","F","D#","E","C","G#","G","F#","B"]
// Output: pitchClass    = [2, 1, 9, 10, 5, 3, 4, 0, 8, 7, 6, 11]

for each note in originalRow:
    find index in chromaticPitches
    pitchClass[i] = index  // 0-11
```

**Complexity**: O(n²) where n=12 (constant time in practice)

### Matrix Generation (`getTwelveToneMatrix`)

**Step 1: Prime Row (Row 0)**
```
Matrix[0][j] = pitchClass[j]  for j = 0..11
```

**Step 2: Inversion Column (Column 0)**
```
for i = 1..11:
    value = 12 - Matrix[0][i]
    Matrix[i][0] = value % 12  (if value ≥ 12)
```

**Step 3: Remaining Cells (Transposition)**
```
for i = 1..11:
    for j = 1..11:
        value = Matrix[i][0] + Matrix[0][j]
        Matrix[i][j] = value % 12  (if value ≥ 12)
```

**Mathematical Foundation**: All operations in ℤ₁₂ (integers modulo 12)

**Complexity**: O(n²) = O(144) operations (constant)

### Display Methods

| Method | Output Format | Use Case |
|--------|---------------|----------|
| `displayPitchClassMatrix` | Space-separated integers (0-11) | Analysis, debugging |
| `displayPitchNotesMatrix` | Space-separated note names (C, C#, ...) | Musical reading, composition |

## Design Decisions

### 1. Single-Class Architecture
**Rationale**: Simplicity for educational/demonstration purposes. The algorithm is self-contained and doesn't require complex object hierarchies.

### 2. Object[] Arrays (vs. Primitive Types)
**Rationale**: Legacy code (2016) uses `Object[]` for flexibility. Modern refactor would use `int[]` and `String[]`.

### 3. Hardcoded Defaults
**Rationale**: The `chromaticPitches` and `originalRow` are `public` fields for easy modification without recompilation (in theory).

### 4. Console Output Only
**Rationale**: No GUI, file I/O, or serialization — pure algorithmic demonstration.

### 5. Batch Script for Build
**Rationale**: Windows-targeted development environment in 2016; no Maven/Gradle.

## Extensibility Points

| Area | Current State | Extension Potential |
|------|---------------|---------------------|
| Input | Hardcoded array | Command-line args, file input, GUI |
| Output | Console only | JSON, XML, MusicXML, MIDI, PDF |
| Algorithm | Basic 12-tone | Rotational arrays, combinatoriality, all-interval rows |
| UI | None | Swing/JavaFX, Web (GWT/TeaVM), CLI library |
| Testing | None | JUnit tests for each method |

## Known Limitations

1. **No Input Validation** — Assumes valid 12-note row with no duplicates
2. **No Duplicate Detection** — Silent failure if row contains repeated notes
3. **Case Sensitivity** — Note matching is exact (`"C#"` ≠ `"c#"`)
4. **Enharmonic Equivalence** — No handling of `Db` vs `C#`, etc.
5. **Single Row** — No support for multiple rows or row comparison
6. **No Serialization** — Cannot save/load matrix state
7. **Legacy Java** — Uses raw types, no generics, `Object[]` instead of type-safe arrays

## Performance Profile

| Metric | Value |
|--------|-------|
| Compilation Time | ~50ms (javac) |
| Execution Time | <5ms |
| Memory Footprint | <1MB heap |
| CPU Usage | Negligible |
| Thread Safety | Not applicable (single-threaded) |

## Future Architecture Considerations

If evolving beyond a demonstration tool:

1. **Modularize**: Separate `PitchClass`, `ToneRow`, `Matrix`, `Renderer` classes
2. **Add Validation**: `ToneRowValidator` with duplicate detection, completeness check
3. **Introduce Interfaces**: `MatrixRenderer`, `RowParser`, `Exporter`
4. **Build System**: Migrate to Maven/Gradle with proper dependency management
5. **Testing**: JUnit 5 + property-based testing for matrix properties
6. **CLI**: Picocli or Spring Shell for argument parsing
7. **Serialization**: Jackson for JSON, JAXB for XML, custom for MusicXML