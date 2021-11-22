package com.formbuilder.easyservey.service;

import com.formbuilder.easyservey.entity.UserResponse;
import com.formbuilder.easyservey.repo.IUserRepository;
import com.formbuilder.easyservey.repo.QuestionsRepository;
import com.formbuilder.easyservey.repo.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAnswerServiceImpl implements IUserAnswerService{
    private final UserAnswerRepository answerRepository;
    private final IUserRepository userRepository;
    private final QuestionsRepository questionsRepository;



    @Override
    public ResponseEntity<?> save(UserResponse userResponse) {
        try {
            log.error(userResponse.toString());
            answerRepository.save(userResponse);
            return new ResponseEntity<String>("Answer Submitted SuccessFully", HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error in User Answer Save API");
        }
        return new ResponseEntity<String>("Error in User Answer Save API", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAll(int fId) {


        try {
            List<UserResponse> responses = answerRepository.GetAllResponsesById(fId);

            if (responses.size() == 0) {
                return new ResponseEntity<String>("No data", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<UserResponse>>(responses, HttpStatus.FOUND);
            }
        }catch (Exception e){
            log.error("Error in User Response Get ALl API "+e);
        }
        return new ResponseEntity<String>("User Response API Error", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ByteArrayInputStream  ExportExcelById(int fId) {
        List<UserResponse> responses= answerRepository.GetAllResponsesById(fId);

        if(responses.size()!=0){
            try(Workbook workbook = new XSSFWorkbook()){
                Sheet sheet = workbook.createSheet("Contacts");

                Row row = sheet.createRow(0);

                // Define header cell style
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                // Creating header cells
                Cell cell = row.createCell(0);
                cell.setCellValue("User ID");
                cell.setCellStyle(headerCellStyle);

                cell = row.createCell(1);
                cell.setCellValue("Form ID");
                cell.setCellStyle(headerCellStyle);

                cell = row.createCell(2);
                cell.setCellValue("QuestionName");
                cell.setCellStyle(headerCellStyle);

                cell = row.createCell(3);
                cell.setCellValue("Answer");
                cell.setCellStyle(headerCellStyle);



                // Creating data rows for each contact
                for(int i = 0; i < responses.size(); i++) {
                    Row dataRow = sheet.createRow(i + 1);
                    dataRow.createCell(0).setCellValue(responses.get(i).getUId());
                    dataRow.createCell(1).setCellValue(responses.get(i).getFId());
                    dataRow.createCell(2).setCellValue(responses.get(i).getQuestionName());
                    String ans="";
                    for (int j=0;j<responses.get(i).getUserAnswer().size();j++){
                        ans+=responses.get(i).getUserAnswer().get(j).toString()+",";
                    }
                    dataRow.createCell(3).setCellValue(ans);

                }

                // Making size of column auto resize to fit with data
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);


                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                return new ByteArrayInputStream(outputStream.toByteArray());
            } catch (IOException ex) {
                log.error("Error during export Excel file", ex);
                return null;
            }
        }
        return null;
    }


}
