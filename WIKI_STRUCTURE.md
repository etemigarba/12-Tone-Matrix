# GitHub Wiki Structure Outline

This document outlines the recommended structure for the 12 Tone Matrix GitHub Wiki. Create these pages after enabling the Wiki in repository settings.

---

## Wiki Home Page (Home.md)

**Title**: 12 Tone Matrix — Documentation Wiki

**Content Structure**:
- Project overview (2-3 sentences)
- Quick links to main sections
- Copyright notice
- Link to main README

---

## Core Pages

### 1. Getting Started (Getting-Started.md)
- Prerequisites (Java JDK 8+)
- Installation methods (Git clone, Download ZIP)
- Compilation instructions (Windows batch, Manual javac)
- Running the application
- First output explanation
- Troubleshooting common issues

### 2. User Guide (User-Guide.md)
- Understanding the Twelve-Tone Matrix
- Musical theory background (Schoenberg, Babbitt)
- Default tone row explanation
- Reading pitch class output (0–11)
- Reading pitch notes output (C, C#, D...)
- Matrix forms: Prime, Inversion, Retrograde, Retrograde Inversion
- Customizing the tone row (source code editing)
- Famous tone rows to try (Schoenberg, Webern, Berg, Stravinsky)

### 3. Technical Reference (Technical-Reference.md)
- Class: `TwelveToneMatrix`
- Fields: `chromaticPitches`, `originalRow`
- Constructor: `TwelveToneMatrix()`
- Methods:
  - `getPichClassOriginalRow()`
  - `getTwelveToneMatrix()`
  - `displayPitchClassMatrix()`
  - `displayPitchNotesMatrix()`
- Algorithm details (modular arithmetic)
- Mathematical properties (Latin square)

### 4. Architecture (Architecture.md)
- System overview diagram
- Data flow: Note names → Pitch classes → Matrix → Display
- Design decisions (single-class, Object arrays, console output)
- Extensibility points
- Known limitations
- Performance profile

### 5. API Reference (API-Reference.md)
- Complete method signatures
- Parameter descriptions
- Return value descriptions
- Usage examples (4 patterns)
- Matrix forms reference table
- Error handling notes

---

## Development Pages

### 6. Contributing (Contributing.md)
- Permission requirements (CRITICAL)
- Development setup
- Code standards
- Pull request process
- Testing requirements
- Documentation standards
- Versioning policy

### 7. Development History (Development-History.md)
- Version timeline (2016)
- Archive contents description
- Pre-release versions (v0.0–v0.4)
- Release v1.0.0 (2016-04-08)
- Repository publication (2026)

### 8. Building & Testing (Building-Testing.md)
- Compilation commands
- Warning flags explanation
- Running tests (when added)
- IDE configuration (IntelliJ, Eclipse, VS Code)
- Docker build (optional)
- CI/CD integration (GitHub Actions example)

### 9. Dependencies (Dependencies.md)
- Java version requirements
- Zero external dependencies
- Standard library usage
- Optional development tools
- Container/Docker support

---

## Theory & Reference Pages

### 10. Twelve-Tone Theory (Theory.md)
- Historical context (Schoenberg 1923)
- Basic concepts: Tone row, pitch class, modulo 12
- Four transformations: P, I, R, RI
- Matrix structure (Babbitt square)
- Combinatoriality
- All-interval rows
- References & further reading

### 11. Tone Row Library (Tone-Row-Library.md)
- Curated collection of famous rows
- Format for Java source code
- Schoenberg rows (Op. 23, 25, 29)
- Webern rows (Op. 17, 21, 28)
- Berg rows (Violin Concerto, Lyric Suite, Lulu)
- Stravinsky rows
- Other composers
- Submission guidelines (with permission)

### 12. Mathematical Foundations (Mathematics.md)
- Modular arithmetic (ℤ₁₂)
- Group theory basics
- Latin square properties
- Interval vectors
- Invariance under transposition
- Combinatorial hexachords

---

## Operational Pages

### 13. License & Legal (License-Legal.md)
- Full license text
- Copyright notice
- Permission request process
- Contributor License Agreement
- Third-party notices (none)

### 14. FAQ (FAQ.md)
- "Why Object[] instead of generics?"
- "How do I use flats (Db, Eb)?"
- "Can I save the matrix to a file?"
- "Is there a GUI version?"
- "How do I report a bug?"
- "Can I use this commercially?"

### 15. Changelog (Changelog.md)
- Mirror of CHANGELOG.md
- Detailed version history
- Upgrade guides (when applicable)

### 16. Support (Support.md)
- Issue reporting guidelines
- Feature request process
- Contact information
- Community resources
- Professional services (Etemi Joshua Garba)

---

## Wiki Navigation Structure

```
Home
├── Getting Started
├── User Guide
├── Technical Reference
│   ├── Architecture
│   ├── API Reference
│   └── Building & Testing
├── Development
│   ├── Contributing
│   ├── Development History
│   └── Dependencies
├── Theory & Reference
│   ├── Twelve-Tone Theory
│   ├── Tone Row Library
│   └── Mathematical Foundations
└── Operational
    ├── License & Legal
    ├── FAQ
    ├── Changelog
    └── Support
```

---

## Wiki Configuration

### Enable Wiki
1. Repository Settings → Features → Wikis ✅
2. Create first page (Home.md) via Wiki UI
3. Set sidebar (_Sidebar.md) and footer (_Footer.md)

### Sidebar Template (_Sidebar.md)

```markdown
# 12 Tone Matrix Wiki

## 📖 Documentation
* [Getting Started](Getting-Started)
* [User Guide](User-Guide)
* [Technical Reference](Technical-Reference)
* [Architecture](Architecture)
* [API Reference](API-Reference)

## 🛠 Development
* [Contributing](Contributing)
* [Development History](Development-History)
* [Building & Testing](Building-Testing)
* [Dependencies](Dependencies)

## 🎵 Theory & Reference
* [Twelve-Tone Theory](Theory)
* [Tone Row Library](Tone-Row-Library)
* [Mathematical Foundations](Mathematics)

## ⚖️ Operational
* [License & Legal](License-Legal)
* [FAQ](FAQ)
* [Changelog](Changelog)
* [Support](Support)

---
*Copyright © 2016 Etemi Joshua Garba*
```

### Footer Template (_Footer.md)

```markdown
---
**12 Tone Matrix** | [GitHub Repository](https://github.com/etemigarba/12-Tone-Matrix) | [Main README](../README.md)

*Copyright © 2016 Etemi Joshua Garba. All rights reserved.*
*Explicit permission required for adoption, editing, and refactoring.*
```

---

## Page Creation Priority

| Priority | Pages | Reason |
|----------|-------|--------|
| **High** | Home, Getting Started, User Guide, Technical Reference | Core user needs |
| **Medium** | Architecture, API Reference, Contributing, Theory | Developer/contributor needs |
| **Low** | Tone Row Library, Mathematics, FAQ, Support | Reference/extended content |

---

## Maintenance

- Sync wiki with repository docs/ directory
- Update on each release
- Review quarterly for accuracy
- Archive outdated pages with notice

---

## Copyright

All wiki content is subject to the same [Proprietary License](../LICENSE) as the codebase.

```
Copyright © 2016 Etemi Joshua Garba. All rights reserved.
Explicit permission required for adoption, editing, and refactoring.
```