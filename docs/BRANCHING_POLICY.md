# Echo Repository Branching and Pull Request Policy

## 1. Integration Baseline
- The official integration baseline branch for the repository is **dev**. All feature branches must branch off from `dev`, and all completed work must be merged back into `dev` via a reviewed Pull Request. No code goes directly to production without passing through this integration branch.

## 2. Branch Naming Conventions
Developers must prefix their working branches based on their specific task type to maintain repository organization:
- `feature/` for new application features (e.g., `feature/ui-redesign`)
- `fix/` for bug fixes and error corrections (e.g., `fix/db-migration`)
- `ml/` for machine learning pipeline and acoustic analysis work (e.g., `ml/acoustic-classifier`)
- `devops/` for CI/CD pipeline adjustments and infrastructure tasks (e.g., `devops/workflow-tweaks`)

## 3. Pull Request (PR) Requirements & Guardrails
- **Mandatory PR Submission:** Direct pushes to the `dev` branch are strictly prohibited. Every change must go through a Pull Request.
- **Automated CI Checks:** The GitHub Actions workflow (`.github/workflows/pilot-infrastructure.yml`) must run successfully and pass all Python/Django tests and Android JVM checks without errors.
- **Peer & Lead Reviews:** At least one peer review or project lead approval is required before a merge button can be clicked.
- **Security & Secrets Protection:** Never commit API keys, Keystore passwords, or database credentials. Code security scans will automatically block commits containing sensitive secrets.

## 4. Merge Method & Release Tagging
- **Merge Strategy:** Use **"Squash and merge"** when merging Pull Requests into `dev` to keep the commit history clean, linear, and easy to trace.
- **Release Tagging:** Semantic version tags (e.g., `v1.0-alpha`) will be applied exclusively by the project lead upon major milestone completions.
