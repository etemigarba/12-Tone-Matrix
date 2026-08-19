# Contributing Guidelines

## ⚠️ CRITICAL: Copyright & Permission Notice

**This project is the exclusive intellectual property of Etemi Joshua Garba.**

> **ANY contribution, adoption, editing, refactoring, forking, or redistribution of this codebase REQUIRES EXPLICIT WRITTEN PERMISSION from Etemi Joshua Garba.**

Before submitting any contribution, you MUST:
1. Obtain written permission from Etemi Joshua Garba
2. Sign a Contributor License Agreement (CLA) if required
3. Agree that all contributions become property of Etemi Joshua Garba

**Unauthorized contributions will be rejected and may result in legal action.**

---

## Code of Conduct

All contributors must adhere to professional standards:
- Respectful communication
- No harassment or discrimination
- Constructive feedback only
- Protect confidential information

---

## Development Setup

### Prerequisites
- Java JDK 8+ (see [DEPENDENCIES.md](DEPENDENCIES.md))
- Git
- Text editor or IDE (IntelliJ IDEA, Eclipse, VS Code)

### Local Development
```bash
# 1. Clone ONLY with permission
git clone https://github.com/etemigarba/12-Tone-Matrix.git

# 2. Create feature branch
git checkout -b feature/your-feature-name

# 3. Make changes
# Edit TwelveToneMatrix.java

# 4. Compile and test
javac -Xlint:deprecation -Xlint:unchecked TwelveToneMatrix.java
java TwelveToneMatrix

# 5. Commit with descriptive message
git add TwelveToneMatrix.java
git commit -m "feat: add input validation for tone row duplicates"

# 6. Push and create PR (requires permission)
git push origin feature/your-feature-name
```

---

## Contribution Process

### 1. Pre-Contribution Requirements
- [ ] Written permission from Etemi Joshua Garba obtained
- [ ] Issue discussed and approved (for new features)
- [ ] CLA signed (if applicable)

### 2. Types of Contributions

| Type | Description | Permission Level |
|------|-------------|------------------|
| **Bug Fixes** | Correct algorithmic errors, fix crashes | Explicit permission required |
| **Enhancements** | Add features (input validation, export, etc.) | Explicit permission + design approval |
| **Documentation** | Improve docs, comments, examples | Explicit permission required |
| **Refactoring** | Code modernization (generics, streams, etc.) | Explicit permission + architecture review |
| **Tests** | Add JUnit tests | Explicit permission required |

### 3. Pull Request Process

1. **Fork/Clone** only with explicit permission
2. **Branch** from `main` with descriptive name: `type/short-description`
3. **Develop** following code standards below
4. **Test** thoroughly — include edge cases
5. **Document** changes in code and update relevant docs
6. **Submit PR** with:
   - Clear title and description
   - Reference to permission grant
   - Before/after behavior demonstration
   - Test results

### 4. Review Criteria

All PRs are evaluated on:
- [ ] Permission documentation provided
- [ ] Code correctness (algorithm integrity)
- [ ] Code quality (style, naming, comments)
- [ ] Test coverage
- [ ] Documentation updates
- [ ] Backward compatibility
- [ ] Performance impact

---

## Code Standards

### Java Style (Legacy Codebase)

**Current State**: Java 2016 style — raw types, `Object[]`, no generics.

**For New Code**: Use modern Java (8+) but maintain compatibility:
```java
// Preferred for new methods
public List<Integer> getPitchClassRow(String[] chromatic, String[] row) { ... }

// Acceptable for modifying existing
public Object[] getPichClassOriginalRow(Object[] chromaticPitches, Object[] originalRow) { ... }
```

### Naming Conventions

| Element | Convention | Example |
|---------|------------|---------|
| Classes | PascalCase | `TwelveToneMatrix` |
| Methods | camelCase | `getTwelveToneMatrix` |
| Fields | camelCase | `originalRow` |
| Constants | UPPER_SNAKE_CASE | `CHROMATIC_PITCHES` |
| Packages | lowercase | `com.ethereal.twelvetone` (future) |

### Code Quality

- **Comments**: Javadoc for public methods
- **Validation**: Input validation for all public methods
- **Error Handling**: Descriptive exceptions, no silent failures
- **Testing**: Unit tests for all new functionality

### Example: Adding Input Validation

```java
/**
 * Converts note names to pitch class integers with validation.
 * 
 * @param chromaticPitches 12 note names in chromatic order
 * @param originalRow 12-note tone row (must be permutation of chromaticPitches)
 * @return Array of 12 pitch class integers (0-11)
 * @throws IllegalArgumentException if row is invalid
 */
public int[] getPichClassOriginalRow(String[] chromaticPitches, String[] originalRow) {
    // Validate inputs
    Objects.requireNonNull(chromaticPitches, "chromaticPitches cannot be null");
    Objects.requireNonNull(originalRow, "originalRow cannot be null");
    
    if (chromaticPitches.length != 12) {
        throw new IllegalArgumentException("chromaticPitches must have exactly 12 elements");
    }
    if (originalRow.length != 12) {
        throw new IllegalArgumentException("originalRow must have exactly 12 elements");
    }
    
    // Check for duplicates
    Set<String> seen = new HashSet<>();
    for (String note : originalRow) {
        if (!seen.add(note)) {
            throw new IllegalArgumentException("Duplicate note in tone row: " + note);
        }
    }
    
    // Validate all notes exist in chromatic scale
    Set<String> validNotes = new HashSet<>(Arrays.asList(chromaticPitches));
    for (String note : originalRow) {
        if (!validNotes.contains(note)) {
            throw new IllegalArgumentException("Invalid note name: " + note);
        }
    }
    
    // Convert (implementation...)
    int[] pitchClass = new int[12];
    // ... conversion logic
    return pitchClass;
}
```

---

## Testing Requirements

### Current State: No Tests

**All contributions adding functionality MUST include tests:**

```java
// TwelveToneMatrixTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TwelveToneMatrixTest {
    
    @Test
    void testDefaultRowGeneratesValidMatrix() {
        TwelveToneMatrix matrix = new TwelveToneMatrix();
        Object[] pc = matrix.getPichClassOriginalRow(
            matrix.chromaticPitches, 
            matrix.originalRow
        );
        Object[][] m = matrix.getTwelveToneMatrix(pc);
        
        assertEquals(12, m.length);
        assertEquals(12, m[0].length);
        
        // Each row must contain all 12 pitch classes
        for (Object[] row : m) {
            Set<Object> unique = new HashSet<>(Arrays.asList(row));
            assertEquals(12, unique.size(), "Row must have 12 unique pitch classes");
        }
    }
    
    @Test
    void testInversionColumnCorrect() {
        // Verify column 0 = inversion of row 0
    }
    
    @Test
    void testLatinSquareProperty() {
        // Each row and column contains each pitch class exactly once
    }
}
```

### Running Tests
```bash
# With JUnit 5 (add to classpath)
java -jar junit-platform-console-standalone.jar --class-path . --scan-class-path
```

---

## Documentation Standards

### Required Updates for Any Change

| Change Type | Files to Update |
|-------------|-----------------|
| New method | `docs/api-reference.md`, Javadoc |
| Algorithm change | `docs/architecture.md`, `README.md` |
| New feature | `docs/getting-started.md`, `README.md` |
| Bug fix | `CHANGELOG.md` |
| Configuration | `DEPENDENCIES.md` |

### Javadoc Template

```java
/**
 * Brief one-line description.
 * 
 * <p>Detailed explanation of behavior, algorithms, and edge cases.</p>
 * 
 * @param paramName Description of parameter
 * @return Description of return value
 * @throws ExceptionType Condition for throwing
 * @since Version added
 * @see RelatedClass#relatedMethod
 */
```

---

## Versioning

This project follows **Semantic Versioning** (MAJOR.MINOR.PATCH):

| Version | When |
|---------|------|
| **MAJOR** | Breaking API changes, algorithm changes |
| **MINOR** | New features, enhancements (backward compatible) |
| **PATCH** | Bug fixes, documentation, refactoring |

Current version: **1.0.0** (2016)

---

## Release Process

1. **Permission** from Etemi Joshua Garba for release
2. **Update** `CHANGELOG.md` with version and changes
3. **Tag** release: `git tag -a v1.1.0 -m "Release v1.1.0"`
4. **Build** artifacts (if applicable)
5. **Publish** with approval

---

## Getting Help

- **Permission Questions**: Contact Etemi Joshua Garba directly
- **Technical Questions**: Open GitHub Issue (with permission)
- **Algorithm Questions**: See references in [README.md](README.md#references)

---

## Attribution

All contributors agree that:
1. Contributions are work-for-hire for Etemi Joshua Garba
2. Copyright transfers to Etemi Joshua Garba
3. Moral rights are waived to the extent permitted by law
4. Contributions may be used commercially without additional compensation

---

## License

By contributing, you agree your contributions are licensed under the same [Proprietary License](LICENSE) as the project.

---

**Remember: NO contribution is accepted without EXPLICIT WRITTEN PERMISSION from Etemi Joshua Garba.**