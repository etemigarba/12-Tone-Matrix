# Dependencies & Prerequisites

## Runtime Requirements

### Java Development Kit (JDK)

| Requirement | Specification |
|-------------|---------------|
| **Minimum Version** | Java SE 8 (1.8) |
| **Tested Versions** | 8, 11, 17, 21 (LTS releases) |
| **Vendor** | Oracle JDK, OpenJDK, Eclipse Temurin, Azul Zulu, Amazon Corretto |
| **Architecture** | x64, ARM64 (Apple Silicon via Rosetta or native) |

**Verification**:
```bash
java -version
javac -version
```

### Operating System Support

| OS | Status | Notes |
|----|--------|-------|
| Windows 10/11 | ✅ Fully Supported | Batch script provided |
| Linux (Ubuntu, Debian, Fedora, Arch, etc.) | ✅ Fully Supported | Manual compile/run |
| macOS (Intel & Apple Silicon) | ✅ Fully Supported | Manual compile/run |
| Other UNIX-like | ✅ Likely Supported | Standard JVM |

---

## Build Requirements

### No Build Tools Required

This project uses **direct `javac` compilation** — no Maven, Gradle, Ant, or other build tools needed.

```bash
# Simple compilation
javac TwelveToneMatrix.java

# With recommended warnings
javac -Xlint:deprecation -Xlint:unchecked TwelveToneMatrix.java
```

### Source Compatibility

| Java Version | Source Compatibility | Target Compatibility |
|--------------|---------------------|---------------------|
| 8 | ✅ | ✅ |
| 11 | ✅ | ✅ |
| 17 | ✅ | ✅ |
| 21 | ✅ | ✅ |

**Note**: Code uses legacy `Object[]` arrays and raw types (2016 style). Compiles on all versions with `-Xlint:unchecked` warnings.

---

## External Dependencies

### Zero External Dependencies

| Category | Dependencies |
|----------|--------------|
| **Runtime Libraries** | None (Java SE only) |
| **Third-Party JARs** | None |
| **Native Libraries** | None |
| **System Services** | None |
| **Network Access** | Not required |
| **Database** | Not required |
| **File System** | Read source, write .class only |

### Java Standard Library Usage

```java
// Only imports used:
import java.util.Vector;      // Legacy, not actually used
import java.util.Hashtable;   // Legacy, not actually used
import java.util.Enumeration; // Legacy, not actually used
import java.util.*;           // Used: Arrays, List
import java.util.List;        // Interface reference
import java.util.ArrayList;   // Not actually used
import java.util.Arrays;      // Used implicitly
```

**Actual Runtime Dependencies**: `java.lang.*` only (Object, String, Integer, System)

---

## Development Dependencies (Optional)

### For Contributors (With Permission Only)

| Tool | Purpose | Required |
|------|---------|----------|
| **Git** | Version control | Yes |
| **IDE** | IntelliJ IDEA, Eclipse, VS Code | Recommended |
| **JUnit 5** | Unit testing (if adding tests) | For testing |
| **JaCoCo** | Code coverage | Optional |
| **Checkstyle** | Code style enforcement | Optional |
| **SpotBugs** | Static analysis | Optional |

### IDE Configuration

**IntelliJ IDEA**:
1. File → Open → Select project folder
2. JDK auto-detected
3. Run `TwelveToneMatrix.main()` directly

**Eclipse**:
1. File → Import → General → Existing Projects into Workspace
2. Select project folder
3. Run As → Java Application

**VS Code** (with Java Extension Pack):
1. Open folder
2. Java extension auto-configures
3. Click ▶️ above `main` method

---

## Deployment Requirements

### Distribution Formats

| Format | Command | Output |
|--------|---------|--------|
| **Source Distribution** | `git archive` | `.zip` / `.tar.gz` with `.java` |
| **Compiled Classes** | `javac *.java` | `.class` files |
| **JAR (if created)** | `jar cfe TwelveToneMatrix.jar TwelveToneMatrix *.class` | Executable JAR |
| **Native Image (GraalVM)** | `native-image TwelveToneMatrix` | Native executable |

### Minimal Distribution Package

```
12-Tone-Matrix/
├── TwelveToneMatrix.java    # Source (3.6 KB)
├── TwelveToneMatrix.class   # Compiled (2.9 KB)
├── TwelveToneMatrix.bat     # Windows launcher (130 B)
├── README.md                # Documentation
└── LICENSE                  # License
```

**Total Size**: ~15 KB (source + compiled + docs)

---

## Container/Docker (Optional)

### Dockerfile Example

```dockerfile
# Build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY TwelveToneMatrix.java .
RUN javac TwelveToneMatrix.java

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/TwelveToneMatrix.class .
ENTRYPOINT ["java", "TwelveToneMatrix"]
```

### Build & Run

```bash
docker build -t twelvetone-matrix .
docker run --rm twelvetone-matrix
```

---

## CI/CD Compatibility

### GitHub Actions Example

```yaml
# .github/workflows/build.yml
name: Build & Test

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Compile
        run: javac -Xlint:deprecation -Xlint:unchecked TwelveToneMatrix.java
      - name: Run
        run: java TwelveToneMatrix
```

---

## Security Considerations

| Aspect | Status |
|--------|--------|
| **Supply Chain** | No external dependencies — zero supply chain risk |
| **Vulnerabilities** | None known (pure Java SE) |
| **Updates Required** | Only JDK security updates |
| **License Compliance** | Proprietary — see LICENSE |

---

## Troubleshooting Dependencies

### Issue: "Unsupported class file major version XX"
**Cause**: Running class compiled with newer JDK on older JRE
**Fix**: Compile with `-target` flag or use matching JDK/JRE
```bash
javac -target 8 -source 8 TwelveToneMatrix.java
```

### Issue: "javac not found"
**Cause**: JRE installed instead of JDK
**Fix**: Install full JDK (not just JRE)

### Issue: Warnings about unchecked operations
**Cause**: Legacy `Object[]` usage (expected)
**Fix**: Ignore, or refactor to generics (requires permission)

---

## Summary

| Requirement | Status |
|-------------|--------|
| Java JDK 8+ | ✅ Required |
| Build Tools | ❌ Not Required |
| External JARs | ❌ None |
| Network | ❌ Not Required |
| Database | ❌ Not Required |
| Native Libs | ❌ None |
| OS Specific | ❌ Cross-platform |

**Bottom Line**: Copy `TwelveToneMatrix.java` anywhere with Java 8+, compile with `javac`, run with `java`. That's it.