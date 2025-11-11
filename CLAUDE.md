# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a MkDocs-based documentation site project using Material theme. The project uses Python 3.13+ with uv as the dependency manager and MkDocs Material for documentation generation.

## Development Commands

### Environment Setup
```bash
# Activate virtual environment (if not using uv)
source .venv/bin/activate

# Install dependencies (using uv)
uv sync
```

### Documentation Development
```bash
# Serve documentation locally with live reload
source .venv/bin/activate && python -m mkdocs serve

# Build static documentation site
source .venv/bin/activate && python -m mkdocs build

# Deploy to GitHub Pages
source .venv/bin/activate && python -m mkdocs gh-deploy
```

### Python Application
```bash
# Run the main Python application
python main.py
```

## Project Structure

- `mkdocs.yml` - MkDocs configuration with Material theme settings
- `docs/` - Documentation source files in Markdown
- `main.py` - Simple Python application entry point
- `pyproject.toml` - Python project metadata and dependencies
- `.venv/` - Virtual environment (managed by uv)

## MkDocs Configuration

The site is configured with:
- Material theme with custom Chinese font (Noto Sans Traditional Chinese)
- Light/dark mode toggle
- Extensive markdown extensions including code highlighting, math support, and enhanced formatting
- Custom color scheme with automatic dark mode detection

## Dependencies

- Primary: `mkdocs-material>=9.7.0`
- Python: `>=3.13`
- Package manager: uv (with lock file `uv.lock`)