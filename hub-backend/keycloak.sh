#!/bin/sh
set -e

MARKER="/opt/keycloak/data/.realm-imported"

if [ ! -f "$MARKER" ]; then
  echo "First boot: importing realm..."
  /opt/keycloak/bin/kc.sh import --dir /opt/keycloak/data/export
  touch "$MARKER"
else
  echo "Realm already imported, skipping import."
fi

exec /opt/keycloak/bin/kc.sh start-dev