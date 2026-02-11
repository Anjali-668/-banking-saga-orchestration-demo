
# Bank Saga Demo (Conductor + Spring Boot Workers)

This package contains a complete, runnable banking-domain Saga demonstration using Netflix Conductor:

**Use case:** Personal Loan Origination & Disbursement

- Happy path: `create_loan_app → kyc_check → credit_check → underwrite_decision → reserve_funds → disburse_funds → post_ledger → send_success_notification`
- Compensation (on failure): `rollback_ledger → release_funds → cancel_loan_app → send_failure_notification`

## Prerequisites
- Docker & Docker Compose installed on your EC2 (Amazon Linux 2023)
- Conductor stack running (server at `http://localhost:8080`, UI at `http://localhost:5000`)
- Java 17 & Maven (only if you wish to build locally without Docker)

## Quick Start (Docker)
1. **Build the service images** (from this folder):

   ```bash
   cd docker
   docker compose -f docker-compose.services.yaml up -d --build
   ```

   > NOTE: Ensure the `networks.conductor_net.name` matches the Docker network where `conductor-server` is running (see `docker network ls`). Replace `docker_default` if different.

2. **Register metadata (tasks + workflows)**:

   ```bash
   cd ../scripts
   bash register_metadata.sh
   ```

3. **Start a happy-path workflow**:

   ```bash
   bash start_workflow.sh '{"applicant":"ANJALI","amount":80000}'
   ```

4. **Start a workflow designed to fail** (to demonstrate compensation):

   ```bash
   bash start_workflow.sh '{"applicant":"ANJALI","amount":150000}'
   ```

5. **Inspect status** (without UI):

   The `start_workflow.sh` script returns the workflow ID and prints a JSON status. You can also query:

   ```bash
   curl -s "http://localhost:8080/api/workflow/<WORKFLOW_ID>?includeTasks=true" | jq .
   ```

## Building JARs locally (optional)
If you want to run without Docker, set `CONDUCTOR_SERVER_URL=http://localhost:8080/api/` in the environment and run the JARs:

```bash
cd services/loan-service && mvn -q -DskipTests package && java -jar target/loan-service-0.0.1-SNAPSHOT.jar &
cd ../risk-service && mvn -q -DskipTests package && java -jar target/risk-service-0.0.1-SNAPSHOT.jar &
cd ../corebanking-service && mvn -q -DskipTests package && java -jar target/corebanking-service-0.0.1-SNAPSHOT.jar &
```

## Notes
- All services are headless (no HTTP endpoints); they only run Conductor workers.
- `CONDUCTOR_SERVER_URL` defaults to `http://localhost:8080/api/` but is overridden in Docker to `http://conductor-server:8080/api/` so the services can reach the Conductor container by its service name.
- Workers are idempotent **for demo** and simulate success/failure using simple logic. Adjust as needed.
