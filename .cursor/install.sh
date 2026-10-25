#!/usr/bin/env bash
# Idempotent setup for the Java interview-prep repository.
#
# Every question is a standalone single-file Java program that is compiled and
# run on its own (see README: `javac Q##.java && java Q##`). There are no
# third-party dependencies and no long-running services, so the only requirement
# is a working JDK. The Cursor default image already ships JDK 21; this script
# self-heals by installing a JDK only when one is missing, then prints the
# toolchain versions so setup logs prove the environment is ready.
set -euo pipefail

if ! command -v javac >/dev/null 2>&1; then
  echo "javac not found; installing default JDK..."
  sudo apt-get update
  sudo apt-get install -y --no-install-recommends default-jdk
fi

echo "Java toolchain ready:"
java -version
javac -version
