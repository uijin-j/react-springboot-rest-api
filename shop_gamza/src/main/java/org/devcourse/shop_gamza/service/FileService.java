package org.devcourse.shop_gamza.service;

import org.devcourse.shop_gamza.domain.image.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileService {
    @Value("${file.dir}")
    private String fileDir;

    public List<Image> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<Image> storeFileResult = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            if (!file.isEmpty()) {
                storeFileResult.add(storeFile(file));
            }
        }
        return storeFileResult;
    }

    public Image storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        file.transferTo(new File(getFullPath(storeFileName)));

        return Image.builder()
                .uploadFileName(originalFilename)
                .storeFileName(storeFileName)
                .build();
    }

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    private String createStoreFileName(String originalFilename) { //uuid로 서버에 저장될 파일명 생성
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) { //png와 같은 확장자 추출!
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
