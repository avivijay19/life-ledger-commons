package com.github.avivijay19.lifeledger.commons.embeddedId.backup;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 29/06/25, Sunday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class GithubBackUpEmbeddedId implements Serializable {
    private String tableName;

    private LocalDate backUpDate;


}
