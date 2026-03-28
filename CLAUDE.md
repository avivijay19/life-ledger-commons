# Life Ledger Commons

## Quick Reference
- **Java**: 21 | **Spring Boot Parent**: 3.4.0 | **Build**: Maven (multi-module)
- **Group ID**: `com.github.avivijay19`
- **Version**: `0.0.3-SNAPSHOT` (all modules aligned)
- **Base package**: `com.github.avivijay19.lifeledger.commons`
- **Registry**: GitHub Packages (`maven.pkg.github.com/avivijay19/life-ledger-commons`)
- **Type**: Shared library — no runnable application, no Docker image

## Build & Run
```bash
mvn compile                       # compile all modules (includes proto generation)
mvn package                       # compile + test + package JARs
mvn deploy -DskipTests            # publish all modules to GitHub Packages
mvn test                          # run all tests (backup module has 9 test classes)
mvn -pl life-ledger-commons-backup test   # test backup module only
```

## Modules (5)

### 1. life-ledger-commons-constants
Zero dependencies. Domain constants and enumerations shared across all services.

| Type | Classes |
|---|---|
| Constants | `DatabaseConstants`, `FuelConstants`, `MutualFundConstants`, `SheetsConstants`, `TransactionConstants` |
| Regex | `RegexConstants` |
| Enums — backup | `GithubBackupStatus`, `GithubBranchStatus`, `GithubPrStatus` |
| Enums — mutualFund | `InvestmentStatus`, `SipStatus`, `InvestmentSource` |
| Enums — sheets | `SheetTitle` |
| Enums — transaction | `TransactionStatus`, `VerificationStatus` |
| Enums — utils | `DateTime` |

### 2. life-ledger-commons-dto
Depends on: constants, proto. JPA entities, DTOs, and serializers shared across services.

| Type | Classes |
|---|---|
| Entities | `AuditableEntity` (base), `ConfigMutualFund`, `CurrentMutualFund`, `InvestedMutualFund`, `InvestedHistoryMutualFund`, `SipMutualFund`, `PendingInvestment`, `Transaction`, `TransactionCategory`, `Fuel` |
| MF DTOs | `BulkCurrentMutualFund`, `BulkResponse`, `CurrentMutualFundRequestMap`, `CurrentMutualFundResponse`, `InvestedMutualFund`, `InvestedMutualFundRequestMap`, `InvestedHistoryMutualFundRequest`, `MarkCompleteRequest`, `PendingInvestmentRequest`, `PendingInvestmentMarkStatusRequest`, `SipRequest`, `SipResponse`, `Data`, `MFApi`, `Meta` |
| Fuel DTOs | `FuelRecordRequest`, `PeriodFuelRecord`, `PeriodFuelRecordResponse`, `SectionalPeriodRecord` |
| Transaction DTOs | `Transactions` |
| Sheet DTOs | `SheetUpdateRequest` |
| Serializers | `FuelSerializer`, `CurrentMutualFundSerializer`, `InvestedSerializer`, `InvestedHistorySerializer`, `SipSerializer` |

### 3. life-ledger-commons-proto (gRPC)
Standalone. Protobuf definitions and generated gRPC stubs.

- **Proto file**: `src/main/proto/Fuel.proto`
- **Service**: `FuelService` — `FuelTransaction(FuelTransactionRequest) returns (FuelTransactionResponse)`
- **Enums**: `VehicleNumber`, `FuelBrand` (SHELL, RIL, BPCL, IOCL, OTHER)
- **Dependencies**: gRPC 1.79.0, Protobuf 4.34.0, protobuf-maven-plugin 0.6.1

### 4. life-ledger-commons-utils
Standalone. Shared Spring configuration beans.

| Class | Purpose |
|---|---|
| `ApplicationConfig` | Common application configuration |
| `CorsConfig` | CORS setup — allows `localhost:8078` (proxy-service) |

### 5. life-ledger-commons-backup (NEW — March 2026)
Standalone. Reusable backup framework: read from source, partition, export to Parquet, notify listeners.

| Package | Classes | Purpose |
|---|---|---|
| `config/` | `BackupConfig` (builder pattern) | Immutable config: partition strategy, output dir, domain, table, entity class, listeners |
| `export/` | `Exporter` (interface), `ExporterFactory`, `ParquetExporter`, `ParquetImporter` | Parquet I/O with Snappy compression, Avro schema auto-generation |
| `model/` | `BackupResult` (record) | Result: filePath, rowCount, fileSizeBytes, tableName, domain, timestamp |
| `partition/` | `PartitionStrategy` (interface), `FlatPartition`, `DailyPartition`, `YearMonthPartition`, `YearWeekPartition` | File path partitioning by time granularity |
| `service/` | `AbstractBackupService` (template method), `SheetsBackupService`, `RestoreService` | Orchestrates backup/restore workflows |
| `sheets/` | `GoogleSheetClient`, `SheetEntityMapper`, `SheetTypeConverter`, `SyncSheetTemplate`, `SheetSyncConfig` | Google Sheets API client, JPA `@Column` reflection, bidirectional sync framework |
| `source/` | `BackupSource` (interface), `SheetsBackupSource` | Pluggable data sources |
| `status/` | `BackupStatus` (enum), `BackupStatusEvent` (record), `BackupStatusListener` (interface) | Status notification: IN_PROGRESS, SUCCESS, FAILED |

**Key dependencies**: Parquet 1.14.1, Hadoop 3.4.1, Google Sheets API v4, Google Auth 1.43.0

## Package Structure
```
com.github.avivijay19.lifeledger.commons
├── constants/                    # constants module
│   ├── DatabaseConstants
│   ├── FuelConstants
│   ├── MutualFundConstants
│   ├── SheetsConstants
│   └── TransactionConstants
├── enumeration/                  # constants module
│   ├── backup/
│   ├── mutualFund/
│   ├── sheets/
│   ├── transaction/
│   └── utils/
├── regex/                        # constants module
│   └── RegexConstants
├── dto/                          # dto module
│   ├── fuel/
│   ├── mf/{bulk,current,invested,investedHistory,mfapi,pending,sip}/
│   ├── sheets/
│   └── transaction/
├── embeddedId/                   # dto module
│   ├── FuelSerializer
│   └── mutualfund/{CurrentMutualFundSerializer,InvestedSerializer,...}
├── entity/                       # dto module
│   ├── AuditableEntity (base)
│   ├── mutualfund/
│   ├── transactions/
│   └── vehicle/
├── utils/                        # utils module
│   └── config/{ApplicationConfig,CorsConfig}
└── backup/                       # backup module
    ├── config/
    ├── export/
    ├── model/
    ├── partition/
    ├── service/
    ├── sheets/
    ├── source/
    └── status/

com.github.avivijay19.fuel        # proto module (generated)
├── FuelServiceGrpc
├── FuelTransactionRequest
├── FuelTransactionResponse
├── VehicleNumber
└── FuelBrand
```

## Module Dependencies
```
constants ─────────┐
                    ├──► dto
proto ─────────────┘
utils (standalone)
backup (standalone)
```

## Consumer Services

| Module | Consumed By |
|---|---|
| constants | mutual-fund-service, sync-service, transaction-service |
| dto | mutual-fund-service, sync-service, transaction-service |
| proto | mutual-fund-service (gRPC fuel tracking) |
| utils | All Spring Boot services (CORS, app config) |
| backup | sync-service (Google Sheets to Parquet pipeline) |

## CI/CD
GitHub Actions (`.github/workflows/`):
- **`build_push.yml`** — PR targeting `main`: checkout, JDK 21, `mvn package`
- **`publish.yml`** — Push to `main`: checkout, JDK 21 (server-id: github), `mvn package`, `mvn deploy -DskipTests`

Consumer services must configure `server-id: github` in their CI `setup-java` step to resolve dependencies from GitHub Packages.

## Consumer Setup (pom.xml)
```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/avivijay19/life-ledger-commons</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.avivijay19</groupId>
    <artifactId>life-ledger-commons-dto</artifactId>
    <version>0.0.3-SNAPSHOT</version>
</dependency>
```

## Tests
```bash
mvn test                                          # all modules
mvn -pl life-ledger-commons-backup test           # backup module only
```

### Backup Module Tests (9 classes)

| Test Class | Covers |
|---|---|
| `BackupConfigTest` | Builder validation, required fields, defaults |
| `ExporterFactoryTest` | Factory method, unknown format exception |
| `ParquetRoundTripTest` | Write + read Parquet, data integrity, Snappy compression |
| `PartitionStrategyTest` | All 4 strategies: Flat, Daily, YearMonth, YearWeek path resolution |
| `RestoreServiceTest` | Parquet import, entity mapping, batch save |
| `SheetEntityMapperTest` | JPA `@Column` reflection, row-to-map conversion |
| `SheetTypeConverterTest` | String-to-Java type coercion for UUID, BigDecimal, dates, enums |
| `SheetEntityMapperMapToEntityTest` | Reflection-based Map-to-entity conversion via @Column |
| `SyncSheetTemplateTest` | Bidirectional sync orchestrator with mocked GoogleSheetClient |

Note: `maven-surefire-plugin` in backup module uses `-Djava.security.manager=allow` for Hadoop compatibility.

## Code Conventions
- Entities use Lombok `@SuperBuilder` + `@Getter` (no `@Data` on entities to avoid equals/hashCode on mutable fields)
- `AuditableEntity` is the base class for all JPA entities — provides `auditCreateTimestamp` and `auditUpdateTimestamp`
- DTOs and value objects are standalone classes (not records, for Jackson/Gson compatibility)
- Builder pattern for config classes (`BackupConfig`)
- Records for immutable result types (`BackupResult`, `BackupStatusEvent`)
- Interfaces for extension points (`Exporter`, `PartitionStrategy`, `BackupSource`, `BackupStatusListener`)
- Template Method pattern in `AbstractBackupService` — subclasses implement `readSource()`
- Factory pattern in `ExporterFactory` — currently supports `"parquet"` only
- Use `var` for local type inference
- All timestamps use `Instant` with `Asia/Kolkata` zone for partition paths
- No checkstyle configured at library level — consumer services enforce their own
