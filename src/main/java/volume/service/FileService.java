package volume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Transactional()
@RequiredArgsConstructor
public class FileService {

    @Value("${spring.servlet.multipart.location}") //application.yml에 저장한 루트 가져오기
    private String uploadPath;

    public void init(String folder){
        try{
            Files.createDirectories(Paths.get(uploadPath+"/"+folder));
        }catch (IOException e){
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    public String store(MultipartFile file, String fileName, String folder){
        try{
            if (file.isEmpty()){
                throw new RuntimeException("File is Empty");
            }
            Path root = Paths.get(uploadPath+"/"+folder);
            if(!Files.exists(root)){ //폴더가 존재하지 않으면 새로 만든다.
                init(folder);
            }

            InputStream inputStream = file.getInputStream();
            //copy(소스파일, 목적지)
            Files.copy(inputStream,root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileName;
    }

    public Path load(String filename, String folder){
        return Paths.get(uploadPath+"/"+folder).resolve(filename);
    }

    public Resource loadAsResource(String filename, String folder){
        try{
            Path file = load(filename,folder);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                //왜 둘 중에 하나를 다 체크하지?
                return resource;
            }else{
                throw new RuntimeException("Could not read file : "+ filename);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
