package com.example.seller.serviceImpl;

import com.example.seller.dto.BuyerAreaResponse;
import com.example.seller.dto.CarHandlingConditionsResponse;
import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.dto.MatchingConditionResponse;
import com.example.seller.mapper.AppraisalMapper;
import com.example.seller.model.CarDetails;
import com.example.seller.model.SellerCarDetails;
import com.example.seller.model.SellerDetails;
import com.example.seller.model.enums.*;
import com.example.seller.repository.AppraisalRepo;
import com.example.seller.service.AssessedService;
import com.example.seller.service.MatchingConditionService;
import com.example.seller.service.client.BuyerServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppraisalServiceImplTest {

    @Mock
    private AppraisalRepo appraisalRepo;

    @Mock
    private AppraisalMapper appraisalMapper;

    @Mock
    private BuyerServiceClient buyerServiceClient;

    @Mock
    private MatchingConditionService matchingConditionService;

    @Mock
    private AssessedService assessedService;

    @Mock
    private NotificationHelper notificationHelper;

    @InjectMocks
    private AppraisalServiceImpl appraisalService;

    @Test
    void processAppraisalRequestShouldCreateAppraisalRequest() {
        SellerDetails sellerDetails = SellerDetails.builder()
                .sellerPhoneNumber("0765259905")
                .sellerName("Lahiru")
                .sellerEmail("lahirumadushandl@gmail.com")
                .sellerPostNumber("10100")
                .sellerDistrict("Colombo")
                .sellerCity("Colombo")
                .sellerAddress("123 Main Street")
                .build();

        CarDetails carDetails = CarDetails.builder()
                .carType("Sedan")
                .carMaker("Toyota")
                .carModelYear("2015")
                .carTraveledDistance("75000")
                .inspectRemain("3 months")
                .carState("Used")
                .runnable("Yes")
                .wheelDrive("4WD")
                .fuel("Petrol")
                .shift("Automatic")
                .accident("No")
                .displacement("1500cc")
                .bodyColor("White")
                .loan("No")
                .desireDate("2025-05-01")
                .grade("A")
                .exterior("Good")
                .scratch("Minor")
                .dent("None")
                .peel("None")
                .rust("None")
                .interior("Clean")
                .dirt("Low")
                .tear("None")
                .airConditioning("Working")
                .smoke("No")
                .navigation("Yes")
                .autoSlide("Yes")
                .leatherSheet("Yes")
                .handleType("Power")
                .backMonitor("Yes")
                .sunroof("Yes")
                .wheel("Alloy")
                .build();

        SellerCarDetails sellerCarDetails = SellerCarDetails.builder()
                .appraisalId(1L)
                .status("ACTIVE")
                .ip("192.168.1.1")
                .sellerDetails(sellerDetails)
                .carDetails(carDetails)
                .desireDateToSell("2025-06-01")
                .photoFrontView("base64ImageFront")
                .photoBackView("base64ImageBack")
                .inspectionCertPhoto("base64InspectionCert")
                .build();

        SellerCarDetailsRequest sellerCarDetailsRequest = new SellerCarDetailsRequest(
                1L,
                "ACTIVE",
                "192.168.1.1",
                "0765259905",
                "Lahiru",
                "lahirumadushandl@gmail.com",
                "10100",
                "Colombo",
                "Colombo",
                "123 Main Street",
                "Sedan",
                "Toyota",
                "2015",
                "75000",
                "3 months",
                "Used",
                "Yes",
                "4WD",
                "Petrol",
                "Automatic",
                "No",
                "1500cc",
                "White",
                "No",
                "2025-05-01",
                "A",
                "Good",
                "Minor",
                "None",
                "None",
                "None",
                "Clean",
                "Low",
                "None",
                "Working",
                "No",
                "Yes",
                "Yes",
                "Yes",
                "Power",
                "Yes",
                "Yes",
                "Alloy",
                "2025-06-01",
                "base64ImageFront",
                "base64ImageBack",
                "base64InspectionCert"
        );

        BuyerAreaResponse buyerArea1 = new BuyerAreaResponse("10100", "Colombo", "Colombo");
        BuyerAreaResponse buyerArea2 = new BuyerAreaResponse("10200", "Colombo", "Mount Lavinia");

        CarHandlingConditionsResponse specialConditions = new CarHandlingConditionsResponse(
                CarOrigin.BOTH,
                NotMove.OK,
                5,
                2010L,
                2025L,
                2000L,
                2009L,
                0L,
                100000L,
                100001L,
                200000L,
                Exclusivity.EX
        );

        CarHandlingConditionsResponse generalConditions = new CarHandlingConditionsResponse(
                CarOrigin.DOMESTIC,
                NotMove.ANY,
                10,
                2005L,
                2025L,
                2000L,
                2004L,
                0L,
                150000L,
                150001L,
                300000L,
                Exclusivity.NON_EX
        );

        MatchingConditionResponse matchingCondition1 = new MatchingConditionResponse(
                1L,
                "Toyota Dealer A",
                "0771234567",
                5000.0,
                2000.0,
                "10100",
                "Colombo",
                "Colombo",
                "dealer1@example.com",
                Branch.HEAD_BRANCH,
                Speciality.SPECIAL,
                List.of("dealer1@example.com", "manager1@example.com"),
                List.of(buyerArea1, buyerArea2),
                specialConditions,
                generalConditions
        );

        MatchingConditionResponse matchingCondition2 = new MatchingConditionResponse(
                2L,
                "General Car Dealer B",
                "0772345678",
                3000.0,
                1500.0,
                "10200",
                "Colombo",
                "Mount Lavinia",
                "dealer2@example.com",
                Branch.SUB_BRANCH,
                Speciality.GENERAL,
                List.of("dealer2@example.com"),
                List.of(buyerArea2),
                null,
                generalConditions
        );

        List<MatchingConditionResponse> matchingConditions = List.of(matchingCondition1, matchingCondition2);
        List<Long> matchedShopIds = List.of(1L, 2L);

        when(appraisalMapper.toSeller(sellerCarDetailsRequest)).thenReturn(sellerCarDetails);
        when(appraisalRepo.save(any(SellerCarDetails.class))).thenReturn(sellerCarDetails);
        when(buyerServiceClient.getAllMatchingConditions()).thenReturn(matchingConditions);
        when(matchingConditionService.automaticMatch(matchingConditions, sellerCarDetails))
                .thenReturn(matchedShopIds);

        Long result = appraisalService.processAppraisalRequest(sellerCarDetailsRequest);

        assertNotNull(result);
        assertEquals(1L, result);

        verify(appraisalMapper).toSeller(sellerCarDetailsRequest);
        verify(appraisalRepo).save(sellerCarDetails);
        verify(buyerServiceClient).getAllMatchingConditions();
        verify(matchingConditionService).automaticMatch(matchingConditions, sellerCarDetails);
        verify(assessedService).saveAssessedDetails(matchedShopIds, 1L);
        verify(notificationHelper).sendAppraisalSuccessNotification(
                eq("lahirumadushandl@gmail.com"),
                eq(1L),
                anyList()
        );
    }
}