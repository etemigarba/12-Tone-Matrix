# Changelog

All notable changes to the 12 Tone Matrix project are documented here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.0.0] - 2016-04-08

### Added
- Initial release of TwelveToneMatrix.java
- Core twelve-tone matrix algorithm implementation
- Pitch class conversion (note names → integers 0–11)
- 12×12 matrix generation using modular arithmetic (mod 12)
- Dual display modes:
  - Pitch class matrix (numerical 0–11)
  - Pitch notes matrix (musical notation C, C#, D, ...)
- Default tone row: `D, C#, A, A#, F, D#, E, C, G#, G, F#, B`
- Chromatic pitch reference: `C, C#, D, D#, E, F, F#, G, G#, A, A#, B`
- Windows batch build script (TwelveToneMatrix.bat)

### Algorithm Details
- **Prime Row (P₀)**: Direct mapping of input tone row to pitch classes
- **Inversion Column (I₀)**: `12 - P₀[i] (mod 12)` for each position
- **Matrix Completion**: `M[i][j] = (M[i][0] + M[0][j]) mod 12`
- **Properties**: Latin square — each row/column contains all 12 pitch classes exactly once

### Technical Specifications
- **Language**: Java (JDK 1.8 compatible)
- **Dependencies**: None (pure Java standard library)
- **Build**: `javac` direct compilation
- **Entry Point**: `TwelveToneMatrix.main(String[])`
- **Output**: Console (System.out)

---

## Development History (Pre-Release)

### Version 0.4 - 2016-04-08 20:46
**File**: `Archive/TwelveToneMatrix - Copy (4).java` (3,664 bytes)
- Final pre-release version
- Complete algorithm implementation
- Both display methods functional

### Version 0.3 - 2016-04-08 10:01
**File**: `TwelveToneMatrix.java` (3,638 bytes) — **Released as v1.0.0**
- Production-ready code
- Cleaned up from development iterations

### Version 0.2 - 2016-03-26 16:18
**File**: `Archive/TwelveToneMatrix - Copy (3).java` (2,879 bytes)
- Matrix generation logic refined
- Inversion calculation corrected

### Version 0.1 - 2016-03-26 15:20
**File**: `Archive/TwelveToneMatrix - Copy (2).java` (3,232 bytes)
- Initial pitch class conversion
- Basic matrix structure

### Version 0.0 - 2016-03-26 12:05
**File**: `Archive/TwelveToneMatrix - Copy.java` (2,378 bytes)
- Earliest prototype
- Core concept implementation

---

## [Unreleased] - Planned

### Under Consideration (Requires Permission)
- **Input Validation**: Duplicate detection, note name validation
- **Enharmonic Support**: Handle `Db`/`C#`, `Gb`/`F#`, etc.
- **Export Formats**: JSON, MusicXML, MIDI, CSV
- **Command-Line Interface**: Accept tone row as argument
- **Unit Tests**: JUnit 5 test suite
- **Modern Java**: Generics, streams, records (Java 16+)
- **Build System**: Maven/Gradle integration
- **Documentation**: Enhanced API docs, theory guide

---

## Version Timeline

```
2016-03-26 12:05  ──► v0.0  (Prototype)
2016-03-26 15:20  ──► v0.1  (Pitch class conversion)
2016-03-26 16:18  ──► v0.2  (Matrix generation)
2016-04-08 20:46  ──► v0.4  (Final dev version)
2016-04-08 22:01  ──► v1.0.0  (RELEASE)
2026-08-18        ──► Repository publication (GitHub)
```

---

## Release Notes Format

### Categories
- **Added** — New features
- **Changed** — Changes in existing functionality
- **Deprecated** — Soon-to-be removed features
- **Removed** — Removed features
- **Fixed** — Bug fixes
- **Security** — Vulnerability fixes

### Example Entry
```markdown
## [1.1.0] - 2026-XX-XX

### Added
- Command-line argument parsing for custom tone rows
- JSON export of matrix data

### Fixed
- NullPointerException when originalRow contains invalid note names

### Changed
- Refactored to use String[] instead of Object[] for type safety
```

---

## Archive Contents

| File | Date | Size | Description |
|------|------|------|-------------|
| `TwelveToneMatrix - Copy.java` | 2016-03-26 12:05 | 2,378 B | Prototype v0.0 |
| `TwelveToneMatrix - Copy (2).java` | 2016-03-26 15:20 | 3,232 B | v0.1 |
| `TwelveToneMatrix - Copy (3).java` | 2016-03-26 16:18 | 2,879 B | v0.2 |
| `TwelveToneMatrix - Copy (4).java` | 2016-04-08 20:46 | 3,664 B | v0.4 (final dev) |

---

## Year of Development

**Primary Development Period**: March 2016 – April 2016

**Copyright Year**: 2016

**Repository Publication**: August 2026

---

## Copyright

```
Copyright © 2016 Ethereal Multimedia Technology. All rights reserved.
Explicit permission required for adoption, editing, and refactoring.
```

See [LICENSE](LICENSE) for full terms.