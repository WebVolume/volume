package volume.DTO;

import lombok.Data;

@Data
public class UploadMusicDTO {

    private String musicFilePath;
    private long musicLength;
    private long likeCount;
    private long playCount;
    private String title;
    private String userId;
}
