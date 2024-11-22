#!/bin/sh
# ----------------------------------------------------------------------------
# Apache Maven Wrapper Script
# ----------------------------------------------------------------------------

# Licensed under the Apache License, Version 2.0
# For license information, see http://www.apache.org/licenses/LICENSE-2.0
# ----------------------------------------------------------------------------

# Required ENV vars:
# JAVA_HOME: Path to Java JDK installation.
# ----------------------------------------------------------------------------

set -e  # Exit immediately if a command exits with a non-zero status.

# Detect OS and set platform-specific flags
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  MINGW*) mingw=true ;;
  Darwin*) darwin=true ;;
  *) cygwin=false; mingw=false; darwin=false ;;
esac

# Resolve JAVA_HOME if not set
if [ -z "$JAVA_HOME" ]; then
  case "$(uname)" in
    Darwin*) 
      JAVA_HOME=$(/usr/libexec/java_home 2>/dev/null || echo "/Library/Java/Home")
      ;;
    *)
      JAVA_HOME=$(dirname "$(readlink -f "$(which javac)")")/..
      ;;
  esac
  export JAVA_HOME
fi

if [ -z "$JAVA_HOME" ] || [ ! -x "$JAVA_HOME/bin/java" ]; then
  echo "ERROR: JAVA_HOME is not set correctly. Java executable not found." >&2
  exit 1
fi

# Find Maven base directory
find_maven_base_dir() {
  dir="$1"
  while [ "$dir" != "/" ]; do
    if [ -d "$dir/.mvn" ]; then
      echo "$dir"
      return 0
    fi
    dir=$(dirname "$dir")
  done
  return 1
}

BASE_DIR=$(find_maven_base_dir "$(pwd)")
if [ -z "$BASE_DIR" ]; then
  echo "ERROR: Maven base directory not found. Make sure '.mvn' exists." >&2
  exit 1
fi

# Set Maven variables
M2_HOME=${M2_HOME:-"$BASE_DIR"}
MAVEN_OPTS="$(tr -s '\n' ' ' < "$BASE_DIR/.mvn/jvm.config" 2>/dev/null) $MAVEN_OPTS"

# Convert paths for Windows environments
if $cygwin || $mingw; then
  M2_HOME=$(cygpath -w "$M2_HOME")
  JAVA_HOME=$(cygpath -w "$JAVA_HOME")
  BASE_DIR=$(cygpath -w "$BASE_DIR")
fi

# Execute Maven Wrapper
exec "$JAVA_HOME/bin/java" $MAVEN_OPTS \
  -classpath "$BASE_DIR/.mvn/wrapper/maven-wrapper.jar" \
  -Dmaven.home="$M2_HOME" \
  -Dmaven.multiModuleProjectDirectory="$BASE_DIR" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"
