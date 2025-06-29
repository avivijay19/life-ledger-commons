package com.github.avivijay19.lifeledger.commons.entity.backup;

import com.github.avivijay19.lifeledger.commons.embeddedId.backup.GithubBackUpEmbeddedId;
import com.github.avivijay19.lifeledger.commons.enumeration.backup.GithubBackupStatus;
import com.github.avivijay19.lifeledger.commons.enumeration.backup.GithubBranchStatus;
import com.github.avivijay19.lifeledger.commons.enumeration.backup.GithubPrStatus;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 29/06/25, Sunday
 **/

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GithubBackUp {

    private GithubBackUpEmbeddedId githubBackUpEmbeddedId;

    private GithubBackupStatus githubBackupStatus;

    private int totalRecordsUpdated;

    private String githubPrHashNumber;

    private GithubPrStatus githubPrStatus;

    private GithubBranchStatus githubBranchStatus;

    private String githubStatusHashNumber;

}
