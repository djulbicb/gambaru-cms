package com.gambaru.gambaru.cms.adapter.in.web;

import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserAttendanceEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.UserMembershipPaymentEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserAttendanceRepository;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserMembershipPaymentRepository;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.UserRepository;
import com.gambaru.gambaru.cms.application.port.out.InsertUserAttendanceUsecase;
import com.gambaru.gambaru.cms.application.service.generators.PDFGenerator;
import com.gambaru.gambaru.cms.application.service.generators.QRGenerator;
import com.gambaru.gambaru.cms.adapter.out.persistence.entity.BarcodeEntity;
import com.gambaru.gambaru.cms.adapter.out.persistence.repo.BarcodeRepository;
import com.gambaru.gambaru.cms.model.BarcodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public class DadJokesController {
    @Autowired
    BarcodeRepository barcodeRepository;

    @Autowired
    BarcodeService barcodeService;

    @Autowired
    QRGenerator generator;

    public String getZeroFilledId(Long id) {
        return String.format("%07d", id);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAttendanceRepository attendanceRepository;
    @Autowired
    private UserMembershipPaymentRepository membershipPaymentRepository;

    @Autowired
    private InsertUserAttendanceUsecase insertUserAttendanceUsecase;
    @GetMapping("/data")
    public void testss () {

//        BarcodeEntity build = BarcodeEntity.builder().barcodeId(1L).status(BarcodeEntity.Status.NOT_USED).build();
//        barcodeRepository.save(build);

//        UserEntity user = UserEntity.builder()
//                .firstName("Bo")
//                .lastName("Jan")
//                .barcode(barcode)
//                .teamName("kickbox")
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        UserEntity savedUser = userRepository.save(user);

        insertUserAttendanceUsecase.insert(1L);
        BarcodeEntity barcode = barcodeRepository.findById(1L).get();
        userRepository.nativeQuery(1L);

        UserMembershipPaymentEntity payment = UserMembershipPaymentEntity.builder()
                .money(BigDecimal.ONE)
                .timestamp(LocalDateTime.now())
                .barcode(barcode)
                .build();
        membershipPaymentRepository.save(payment);

        UserAttendanceEntity entity = UserAttendanceEntity.builder()
                .timestamp(LocalDateTime.now())
                .barcode(barcode)
                .build();
        attendanceRepository.save(entity);
        attendanceRepository.save(entity);
    }

    @GetMapping("/test")
    public ResponseEntity<byte[]> test () {
        List<BarcodeEntity> barcodeEntityEntities = barcodeService.fetchManybyStatusNotUsed(20);
        List<BufferedImage> list = barcodeEntityEntities.stream().map(barcode -> {
            try {
                return QRGenerator.generateQRCodeImage(getZeroFilledId(barcode.getBarcodeId()), 300, 300);
                // return BarcodeGenerator.generateBarcodeImage(getZeroFilledId(barcode.getId()), 600, 200);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

        String filepath = "/home/sss/Documents/dev/workspace/gambaru-cms/src/main/java/com/gambaru/gambaru/cms/test.pdf";
        byte[] bytes = PDFGenerator.generatePDF(list, filepath);

//        File pdfFile = new File(filepath);
//        FileSystemResource resource = new FileSystemResource(pdfFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=downloaded-file.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
    @GetMapping("/api/dadjokes")
    public String dadJokes() throws Exception {
        BarcodeEntity barcodeEntity = barcodeService.fetchFirstbyStatusNotUsed();
        log.info(barcodeEntity.toString());
        barcodeService.fetchManybyStatusNotUsed(20);

        BufferedImage qrCodeImage = QRGenerator.generateQRCodeImage(barcodeEntity.getBarcodeId().toString(), 250, 250);
        File outputFile = new File("/home/sss/Documents/dev/workspace/gambaru-cms/src/main/java/com/gambaru/gambaru/cms/qrcode.png");
        ImageIO.write(qrCodeImage, "png", outputFile);

        return "Justice is a dish best served cold, if it were served warm it would be just water.";
    }
}