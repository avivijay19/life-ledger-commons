package com.github.avivijay19.lifeledger.commons.backup.config;

import com.github.avivijay19.lifeledger.commons.backup.partition.PartitionStrategy;
import com.github.avivijay19.lifeledger.commons.backup.status.BackupStatusListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BackupConfig {

    private final PartitionStrategy partitionStrategy;
    private final Path outputDir;
    private final String tableName;
    private final String domain;
    private final String triggeredBy;
    private final Class<?> entityClass;
    private final List<BackupStatusListener> listeners;

    private BackupConfig(Builder builder) {
        this.partitionStrategy = Objects.requireNonNull(builder.partitionStrategy, "partitionStrategy");
        this.outputDir = Objects.requireNonNull(builder.outputDir, "outputDir");
        this.tableName = Objects.requireNonNull(builder.tableName, "tableName");
        this.domain = Objects.requireNonNull(builder.domain, "domain");
        this.triggeredBy = builder.triggeredBy != null ? builder.triggeredBy : "system";
        this.entityClass = builder.entityClass;
        this.listeners = List.copyOf(builder.listeners);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PartitionStrategy getPartitionStrategy() {
        return partitionStrategy;
    }

    public Path getOutputDir() {
        return outputDir;
    }

    public String getTableName() {
        return tableName;
    }

    public String getDomain() {
        return domain;
    }

    public String getTriggeredBy() {
        return triggeredBy;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public List<BackupStatusListener> getListeners() {
        return listeners;
    }

    public static final class Builder {

        private PartitionStrategy partitionStrategy;
        private Path outputDir;
        private String tableName;
        private String domain;
        private String triggeredBy;
        private Class<?> entityClass;
        private final List<BackupStatusListener> listeners = new ArrayList<>();

        private Builder() {
        }

        public Builder partitionStrategy(PartitionStrategy partitionStrategy) {
            this.partitionStrategy = partitionStrategy;
            return this;
        }

        public Builder outputDir(Path outputDir) {
            this.outputDir = outputDir;
            return this;
        }

        public Builder tableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder triggeredBy(String triggeredBy) {
            this.triggeredBy = triggeredBy;
            return this;
        }

        public Builder entityClass(Class<?> entityClass) {
            this.entityClass = entityClass;
            return this;
        }

        public Builder addListener(BackupStatusListener listener) {
            this.listeners.add(listener);
            return this;
        }

        public BackupConfig build() {
            return new BackupConfig(this);
        }
    }
}
