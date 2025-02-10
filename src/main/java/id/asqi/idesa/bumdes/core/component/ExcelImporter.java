package id.asqi.idesa.bumdes.core.component;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelImporter<T> {

//	public void genericExcelImporter(
//			MultipartFile file,
//			Class<T> entityClass,
//			JpaRepository<T, ?> repository
//	) throws IOException, InstantiationException, IllegalAccessException {
//		if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx"))
//			throw new InvalidOperationException("Hanya file dengan ekstensi .xlsx yang bisa diimport");
//
//		Workbook workbook = new XSSFWorkbook(file.getInputStream());
//		Sheet sheet = workbook.getSheetAt(0);
//		List<T> entities = new ArrayList<>();
//
//		Field[] fields = entityClass.getDeclaredFields();
////		List<String> fieldsName = Arrays.stream(fields).map(Field::getName).toList();
//
//		for (Row row : sheet) {
//			if (row.getRowNum() == 0) {
//				continue;
//			}
//
//			T entity = entityClass.NE();
//
//
//
//		}
//
//		workbook.close();
//
//		/*save the data*/
//		if (entities.isEmpty())
//			throw new InvalidOperationException("No valid data to import");
//
//		repository.saveAll(entities);
//	}

//    public void importUsers(MultipartFile file) throws IOException {
//          if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx"))
//			throw new InvalidOperationException("Hanya file dengan ekstensi .xlsx yang bisa diimport");
//
//        Workbook workbook = new XSSFWorkbook(file.getInputStream());
//        Sheet sheet = workbook.getSheetAt(0);
//
//        List<User> users = new ArrayList<>();
//
//        for (Row row : sheet) {
//            if (row.getRowNum() == 0) {
//                continue;
//            }
//
//	        /*get username*/
//	        Cell usernameCell = row.getCell(1);
//	        String username = usernameCell.getStringCellValue().trim();
//
//			/*get email*/
//            Cell emailCell = row.getCell(2);
//            String email = emailCell.getStringCellValue().trim();
//
//	        /*get password*/
//	        Cell passwordCell = row.getCell(2);
//	        String password = passwordCell.getStringCellValue().trim();
//
//			username = userRepository.existsByUsername(username) ? "" : username;
//			email = userRepository.existsByEmail(email) ? "" : email;
//
//			/*entity creation*/
//            User user = new User();
//            user.setId(Constants.idGenerator());
//            user.setUsername(username);
//            user.setEmail(email);
//            user.setPassword(password);
//            user.setCreatedAt(LocalDateTime.now());
//            user.setUpdatedAt(LocalDateTime.now());
//            users.add(user);
//        }
//
//        workbook.close();
//
//		/*save the data*/
//		if (users.isEmpty())
//			throw new InvalidOperationException("No valid data to import");
//
//		userRepository.saveAll(users);
//	}
//

}