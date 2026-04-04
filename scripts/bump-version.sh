#!/usr/bin/env bash
# Usage: ./scripts/bump-version.sh [major|minor|patch]
# Bumps the VERSION file and the <version> tag in pom.xml

set -euo pipefail

BUMP="${1:-patch}"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"
VERSION_FILE="${REPO_ROOT}/VERSION"
POM_FILE="${REPO_ROOT}/pom.xml"

if [ ! -f "${VERSION_FILE}" ]; then
  echo "ERROR: VERSION file not found at ${VERSION_FILE}" >&2
  exit 1
fi

CURRENT=$(cat "${VERSION_FILE}" | tr -d '[:space:]')

if ! echo "${CURRENT}" | grep -qE '^[0-9]+\.[0-9]+\.[0-9]+$'; then
  echo "ERROR: Invalid semver in VERSION: '${CURRENT}'" >&2
  exit 1
fi

MAJOR=$(echo "${CURRENT}" | cut -d. -f1)
MINOR=$(echo "${CURRENT}" | cut -d. -f2)
PATCH=$(echo "${CURRENT}" | cut -d. -f3)

case "${BUMP}" in
  major)
    MAJOR=$((MAJOR + 1))
    MINOR=0
    PATCH=0
    ;;
  minor)
    MINOR=$((MINOR + 1))
    PATCH=0
    ;;
  patch)
    PATCH=$((PATCH + 1))
    ;;
  *)
    echo "ERROR: Unknown bump type '${BUMP}'. Use major, minor, or patch." >&2
    exit 1
    ;;
esac

NEW_VERSION="${MAJOR}.${MINOR}.${PATCH}"

echo "${NEW_VERSION}" > "${VERSION_FILE}"
echo "VERSION: ${CURRENT} -> ${NEW_VERSION}"

# Update pom.xml project version (the <version> under <artifactId>, not inside <parent>)
if [ -f "${POM_FILE}" ]; then
  # Replace the project version line (after artifactId, not inside <parent>)
  sed -i.bak "s|<version>${CURRENT}-SNAPSHOT</version>|<version>${NEW_VERSION}</version>|g" "${POM_FILE}" 2>/dev/null || true
  sed -i.bak "s|<version>${CURRENT}</version>|<version>${NEW_VERSION}</version>|g" "${POM_FILE}" 2>/dev/null || true
  rm -f "${POM_FILE}.bak"
  echo "pom.xml: updated project version to ${NEW_VERSION}"
fi

echo "Done. New version: ${NEW_VERSION}"
