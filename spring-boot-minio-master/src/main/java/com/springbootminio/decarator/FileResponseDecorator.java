package com.springbootminio.decarator;

import com.springbootminio.helper.MediaTypeInfo;
import com.springbootminio.mapper.FileResponseMapper;
import com.springbootminio.payload.FileResponse;
import com.springbootminio.util.FileResponseLink;
import io.minio.ObjectStat;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class FileResponseDecorator implements FileResponseMapper {

    @Setter(onMethod = @__({@Autowired}))
    FileResponseLink linkUtils;

    @Override
    public FileResponse fileAddResponse(ObjectStat objectStat) {
        FileResponse response = fileResponse(objectStat);
        String mediaType = MediaTypeInfo.getCurrentMediaType();
        return mediaType != null && mediaType.equals("hal")
                ? linkUtils.addOperationWithLink(response)
                : response;
    }

    @Override
    public FileResponse fileGetResponse(ObjectStat objectStat) {
        FileResponse fileResponse = fileResponse(objectStat);
        String mediaType = MediaTypeInfo.getCurrentMediaType();
        return mediaType != null && mediaType.equals("hal")
                ? linkUtils.getOperationWithLink(fileResponse)
                : fileResponse;
    }

    private FileResponse fileResponse(ObjectStat objectStat) {
        return FileResponse.builder()
                .filename(objectStat.name())
                .fileSize(objectStat.length())
                .contentType(objectStat.contentType())
                .createdTime(objectStat.createdTime())
                .build();
    }
}
