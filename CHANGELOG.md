# Changelog

All notable changes to this project will be documented here.

The format follows [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

---

## [0.1.0] - 2025-06-01

### Added

- `init` command: scaffolds a minimal Spring Boot project structure
- `add-controller <Name>`: generates a REST controller class
- `add-service <Name>`: generates a service class annotated with `@Service`
- `add-entity <Name>`: generates a JPA entity class
- Safe file handling: existing files are never overwritten
- Unit tests for all generators and the command dispatcher
- README with usage examples and project structure overview
- CONTRIBUTING guide
- MIT License

---

## Planned

- Read project package from a config file
- `add-repository <Name>` command
- `add-dto <Name>` command
- Colored terminal output
- `--dry-run` flag
