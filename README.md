# spring-mini-tool

A small command-line tool that helps you scaffold Spring Boot project structures quickly.

Instead of manually creating folders and boilerplate files every time you start a new Spring Boot project, `spring-mini-tool` does it for you in seconds.

---

## Why this exists

Starting a Spring Boot project always involves the same repetitive steps: creating the right folder structure, writing the same `Application.java`, setting up `application.properties`, adding a first controller. This tool automates exactly that — nothing more, nothing less.

It is intentionally small and easy to understand. If you want to learn how a CLI tool in Java works, or if you just want a quick scaffold helper, this is a good starting point.

---

## Requirements

- Java 21 or higher
- Maven 3.6 or higher

---

## Build

Clone the repository and build the JAR:

```bash
git clone https://github.com/your-username/spring-mini-tool.git
cd spring-mini-tool
mvn clean package
```

This creates `target/spring-mini-tool.jar`.

---

## Usage

```bash
java -jar target/spring-mini-tool.jar <command> [arguments]
```

### Available commands

| Command                    | Description                                      |
|----------------------------|--------------------------------------------------|
| `init`                     | Scaffold a new Spring Boot project structure     |
| `add-controller <Name>`    | Generate a REST controller class                 |
| `add-service <Name>`       | Generate a service class                         |
| `add-entity <Name>`        | Generate a JPA entity class                      |

---

## Examples

**Create a new project scaffold in the current directory:**

```bash
mkdir my-new-app && cd my-new-app
java -jar /path/to/spring-mini-tool.jar init
```

This creates:

```
my-new-app/
├── pom.xml
├── .gitignore
├── README.md
└── src/
    ├── main/
    │   ├── java/com/example/myapp/
    │   │   ├── Application.java
    │   │   └── controller/
    │   │       └── HelloController.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/example/myapp/
            └── ApplicationTest.java
```

**Add a controller:**

```bash
java -jar spring-mini-tool.jar add-controller Product
```

Creates `src/main/java/com/example/myapp/controller/ProductController.java`.

**Add a service:**

```bash
java -jar spring-mini-tool.jar add-service Order
```

Creates `src/main/java/com/example/myapp/service/OrderService.java`.

**Add an entity:**

```bash
java -jar spring-mini-tool.jar add-entity Customer
```

Creates `src/main/java/com/example/myapp/model/Customer.java`.

> **Note:** The tool never overwrites existing files. If a file already exists, it prints a skip message and continues.

---

## Project structure

```
spring-mini-tool/
├── src/main/java/dev/springminitool/
│   ├── cli/
│   │   ├── Main.java               Entry point, prints usage
│   │   └── CommandDispatcher.java  Routes commands to handlers
│   ├── command/
│   │   ├── Command.java            Interface for all commands
│   │   ├── InitCommand.java        Handles `init`
│   │   ├── AddControllerCommand.java
│   │   ├── AddServiceCommand.java
│   │   └── AddEntityCommand.java
│   ├── generator/
│   │   ├── ProjectScaffoldGenerator.java
│   │   ├── ControllerGenerator.java
│   │   ├── ServiceGenerator.java
│   │   └── EntityGenerator.java
│   └── util/
│       └── FileWriter.java         Safe file creation utility
└── src/test/java/dev/springminitool/
    ├── command/
    │   └── CommandDispatcherTest.java
    └── generator/
        ├── ControllerGeneratorTest.java
        ├── ServiceGeneratorTest.java
        └── EntityGeneratorTest.java
```

The code is split into three layers:

- **`cli`** — handles user input and command routing
- **`command`** — one class per CLI command, thin wrappers that validate input and call generators
- **`generator`** — the actual file generation logic, fully testable without any CLI involvement

---

## Running the tests

```bash
mvn test
```

---

## What could be added next

- Read `groupId` and `package` from a config file (`.springminitool.json`)
- `add-repository <Name>` to generate a Spring Data JPA repository
- `add-dto <Name>` to generate a DTO class
- Colored terminal output
- A `--dry-run` flag to preview what would be created

---

## Contributing

Contributions are welcome. Please read [CONTRIBUTING.md](CONTRIBUTING.md) first.

---

## License

MIT — see [LICENSE](LICENSE).
