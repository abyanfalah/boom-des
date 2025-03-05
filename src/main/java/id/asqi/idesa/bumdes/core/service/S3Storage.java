package id.asqi.idesa.bumdes.core.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
public class S3Storage {

	private final MinioClient minioClient;
	@Value("${minio.bucket-name}")
	private String bucketName;

	public S3Storage (@Value("${minio.url}") String url,
	                  @Value("${minio.access-key}") String accessKey,
	                  @Value("${minio.secret-key}") String secretKey) {
		this.minioClient = MinioClient.builder()
				.endpoint(url)
				.credentials(accessKey, secretKey)
				.build();
	}

	public String uploadFile (String folderName, MultipartFile file) throws Exception {
		if(file == null){
			return null;
		}

		try (InputStream inputStream = file.getInputStream()) {
			String originalFileName = file.getOriginalFilename();
			String extension = "";
			if (originalFileName.contains(".")) {
				extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			}
			String fileName = UUID.randomUUID().toString().replace("-", "") + extension;
			String objectName = folderName + "/" + fileName;

			minioClient.putObject(
					PutObjectArgs.builder()
							.bucket(bucketName)
							.object(objectName)
							.stream(inputStream, file.getSize(), - 1)
							.contentType(file.getContentType())
							.build()
			);
			return objectName;
		} catch (MinioException e) {
			throw new Exception("Error while uploading file to MinIO", e);
		}
	}

	public String updateFile (String folderName, MultipartFile file, String oldObjectName) throws Exception {
		try {
			minioClient.removeObject(
					RemoveObjectArgs.builder()
							.bucket(bucketName)
							.object(oldObjectName)
							.build()
			);
		} catch (MinioException e) {
			throw new Exception("Error while deleting old file in MinIO", e);
		}

		return uploadFile(folderName, file);
	}


	public static final String LOCAL_STORAGE = System.getProperty("user.home") + "/";

	public String uploadFileBase64 (String fileBase64, String fileName) throws IOException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, ServerException, ErrorResponseException, InvalidKeyException {
		if (fileBase64 == null || fileBase64.isBlank()) return null;

		if (fileBase64.contains(",")) {
			fileBase64 = fileBase64.split(",")[1];
		}

		byte[] data = Base64.getDecoder().decode(fileBase64);
		String tempPath = LOCAL_STORAGE + fileName;

		try (OutputStream stream = new FileOutputStream(tempPath)) {
			stream.write(data);
		}

		minioClient.uploadObject(
				UploadObjectArgs.builder()
						.bucket(bucketName)
						.object(fileName)
						.filename(tempPath)
						.build());
		File myObj = new File(tempPath);
		myObj.delete();
		return fileName;
	}

	public static void createFolderIfNotExists (String folderPath) {
		File folder = new File(folderPath);

		if (! folder.exists()) {
			if (folder.mkdirs()) {
				System.out.println("storage folder created successfully.");
			} else {
				System.out.println("Failed to create storage folder.");
			}
		}
	}

}