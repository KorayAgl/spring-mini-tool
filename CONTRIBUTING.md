# Contributing to spring-mini-tool

Thank you for considering a contribution. This document explains how to get started.

---

## Getting the code

```bash
git clone https://github.com/your-username/spring-mini-tool.git
cd spring-mini-tool
mvn clean test
```

All tests should pass before you start making changes.

---

## How to add a new command

1. Create a new class in `src/main/java/dev/springminitool/command/` that implements `Command`.
2. If the command generates files, create a matching generator class in `dev/springminitool/generator/`.
3. Register the new command in `CommandDispatcher.java` by adding a `case` to the switch statement.
4. Write tests for the generator in `src/test/java/dev/springminitool/generator/`.

That is all that is needed for a basic new command.

---

## Code style

- Keep methods short and focused on one thing.
- Use descriptive names — avoid abbreviations.
- Add a Javadoc comment to public classes and methods when the purpose is not obvious from the name.
- Do not add comments that just repeat what the code already says.

---

## Commit messages

Please write short, lowercase commit messages that describe what changed:

```
add repository generator
fix file skip message formatting
improve error output for missing arguments
```

Avoid messages like `fix stuff` or `update`.

---

## Pull requests

- One feature or fix per pull request.
- Make sure `mvn test` passes before opening a PR.
- Describe what the change does and why in the PR description.

---

## Reporting issues

If you find a bug or have a feature idea, please open an issue on GitHub. Include the command you ran and the error message if applicable.
