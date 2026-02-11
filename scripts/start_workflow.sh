
#!/usr/bin/env bash
set -euo pipefail

CONDUCTOR=${CONDUCTOR:-http://localhost:8080/api}
INPUT=${1:-'{"applicant":"ANJALI","amount":80000}'}

echo "Starting loan_saga_workflow with input: $INPUT"
WF_ID=$(curl -sS -X POST -H "Content-Type: application/json"   -d "$INPUT"   "$CONDUCTOR/workflow/loan_saga_workflow")

echo "Workflow ID: $WF_ID"

# Poll status once after a short delay
sleep 2
curl -sS "$CONDUCTOR/workflow/$WF_ID?includeTasks=true"
