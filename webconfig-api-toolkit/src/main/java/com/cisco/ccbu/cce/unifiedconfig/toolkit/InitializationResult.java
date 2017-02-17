package com.cisco.ccbu.cce.unifiedconfig.toolkit;

import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.InitializationStatus;

import java.util.List;

public class InitializationResult {
    private final List<InitializationStatus> statuses;
    private final boolean successful;

    public InitializationResult(List<InitializationStatus> statuses, boolean successful) {
        this.statuses = statuses;
        this.successful = successful;
    }

    public List<InitializationStatus> getStatuses() {
        return statuses;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
