package com.anjubao.fileupload.service;


import com.anjubao.fileupload.util.DateTools;
import com.anjubao.fileupload.util.PathUtils;
import com.anjubao.fileupload.vo.UploadImageVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
@Service( "uploadImageService" )
public class UploadImageService {


    private static final String IMAGE_DIR = "adApiFile";

    private static final String IMAGE_FILE_NAME = "image";


	@Value("${image.path)")
	String uploadImagePath;

	@Value("${prefix.url}")
	String imagePrefixUrl;


    /**
     * 上传文件
     * 
     * @param fileMap
     * @return
     */
	public UploadImageVo uploadImages(Map<String, MultipartFile> fileMap) {
		UploadImageVo vo = new UploadImageVo();
		String relativeFilePath = IMAGE_DIR + File.separator + IMAGE_FILE_NAME + File.separator + DateTools.getYearAndMonthDay();
		String filePath = uploadImagePath + relativeFilePath;

		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}


		for (Entry<String, MultipartFile> key : fileMap.entrySet()) {
			String fileNameSuffix = FilenameUtils.getExtension(key.getValue().getOriginalFilename());
			String originalFileName = key.getValue().getOriginalFilename();
			
			if(StringUtils.isNotBlank(originalFileName) && originalFileName.lastIndexOf(File.separator) >0){
				log.info("originalFileName name has '/' sign,so we must move it.... ");
				originalFileName=originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1,originalFileName.length());
			}
			
			log.info("user upload real fileName: "+originalFileName);
			long originalFileSize = key.getValue().getSize();// 字节数
			String originalFileType =key.getValue().getContentType();
			
			String suffix = "";
			if (StringUtils.isNotBlank(fileNameSuffix)) {
				suffix = DateTools.getMsDateString(new Date()) + "." + fileNameSuffix;// 后缀文件名
			} else {
				suffix = DateTools.getMsDateString(new Date()) + ".jpg";// 后缀为空时默认文件名后缀为jpg
			}
			String originalPath = File.separator + suffix;// 保存的文件地址 比如:
			String dbFileUrl = relativeFilePath + File.separator + suffix.replace("\\", "/");
			int attachId=-1;
			try {
					log.info("save local file Path: " + filePath);
					FileUtils.copyInputStreamToFile(key.getValue().getInputStream(), new File(filePath + originalPath));
				//保存历史
//				attachId=this.saveAttachment(originalFileName, relativeFilePath + originalPath, originalFileSize, originalFileType);
			} catch (IOException e) {
				log.error("路径异常.", e);
//				throw new CustomException(ErrorNum.PARAMS_ERROR.getIndex(), "路径异常");
			}// 保存原图
			String imagePath = relativeFilePath + originalPath;
			vo.setImagePath(imagePrefixUrl+imagePath);
			vo.setRealFileName(originalFileName);
//			vo.setImageId(attachId);
			log.info("http visit address : " + vo.getImagePath());
		}
		return vo;
	}

//    @Transactional
//    private int saveAttachment(String fileName,String filePath,long fileSize,String fileType){
//    	 AdAttachmentVo attach = new AdAttachmentVo();
//    	 attach.setFileName(fileName);
//    	 attach.setRelativeFilePath(filePath);
//    	 attach.setFileSize(fileSize);
//    	 attach.setFileType(fileType);
//    	 adAttachmentDao.insert(attach);
//    	 log.info("save attachment:attachId: "+attach.getAttachId()+", fileName:"+fileName+",filePath:"+filePath+",fileSize:"+fileSize+",fileType:"+fileType);
//    	 return attach.getAttachId();
//    }
}
