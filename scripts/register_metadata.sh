
#!/usr/bin/env bash
set -euo pipefail

CONDUCTOR=${CONDUCTOR:-http://localhost:8080/api}

echo "Registering taskdefs..."
curl -sS -X POST -H "Content-Type: application/json"   -d @../metadata/taskdefs.json   "$CONDUCTOR/metadata/taskdefs"

echo -e "
Registering main workflow..."
curl -sS -X POST -H "Content-Type: application/json"   -d @../metadata/saga_workflow.json   "$CONDUCTOR/metadata/workflow"

echo -e "
Registering compensation workflow..."
curl -sS -X POST -H "Content-Type: application/json"   -d @../metadata/compensation_workflow.json   "$CONDUCTOR/metadata/workflow"

echo -e "
Done."

curl -X PUT -H "Content-Type: application/json"  -d @../metadata/sequential_workflow.json  "$API/metadata/workflow"
