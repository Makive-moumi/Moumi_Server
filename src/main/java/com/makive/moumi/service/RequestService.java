package com.makive.moumi.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.makive.moumi.exception.Code;
import com.makive.moumi.exception.GeneralException;
import com.makive.moumi.model.domain.Client;
import com.makive.moumi.model.domain.Request;
import com.makive.moumi.model.domain.Review;
import com.makive.moumi.model.domain.Translation;
import com.makive.moumi.model.dto.RequestDTO;
import com.makive.moumi.model.dto.ReviewDTO;
import com.makive.moumi.model.dto.request.RequestRequest;
import com.makive.moumi.model.dto.response.RequestClientResponse;
import com.makive.moumi.model.dto.response.RequestTranslatorResponse;
import com.makive.moumi.model.dto.response.RequestsResponse;
import com.makive.moumi.repository.ClientRepository;
import com.makive.moumi.repository.RequestRepository;
import com.makive.moumi.repository.TranslationRepository;
import com.makive.moumi.repository.specification.RequestSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestService {
    private final TranslationRepository translationRepository;
    private final ClientRepository clientRepository;
    private final RequestRepository requestRepository;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    private static double calculateAverageRating(List<Double> reviewRatings) {
        if (reviewRatings.isEmpty()) {
            return 0.0;
        }
        double sum = reviewRatings.stream().mapToDouble(Double::doubleValue).sum();
        return Double.parseDouble(String.format("%.1f", sum / reviewRatings.size()));
    }

    public RequestsResponse getRequests(RequestRequest requestRequest, Pageable pageable) {
        List<String> category = requestRequest.getCategory();
        boolean hasReview = requestRequest.isHasReview();
        Specification<Request> spec = RequestSpecifications.findAllByClientIdAndCategory(1L, category, hasReview);

        Slice<Request> requestSlice = requestRepository.findAll(spec, pageable);
        List<RequestDTO> requestDTOList = requestSlice.getContent().stream()
                .map(RequestDTO::fromRequest)
                .collect(Collectors.toList());

        return RequestsResponse.builder()
                .content(requestDTOList)
                .first(requestSlice.isFirst())
                .last(requestSlice.isLast())
                .build();
    }

    public RequestClientResponse getRequestClient(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        Translation translation = request.getTranslation();
        Review review = request.getReview();
        List<Double> reviewRatings = translation.getRequests().stream()
                .map(r -> r.getReview() != null ? r.getReview().getRating() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return RequestClientResponse.builder()
                .request(RequestDTO.fromRequest(request))
                .reviewRating(calculateAverageRating(reviewRatings))
                .reviewCount(reviewRatings.size())
                .review(review != null ? ReviewDTO.fromReview(review) : null)
                .requestPdfs(request.getRequestPdfs())
                .responsePdfs(request.getResponsePdfs())
                .build();
    }

    public RequestTranslatorResponse getRequestTranslator(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        int count = request.getRequestPdfs().size();
        int price = request.getTranslation().getPrice();

        return RequestTranslatorResponse.builder()
                .request(RequestDTO.fromRequest(request))
                .count(count)
                .totalPrice(price * count)
                .requestPdfs(request.getRequestPdfs())
                .build();
    }

    @Transactional
    public void addRequest(Long translationId, List<MultipartFile> files) throws IOException {
        Translation translation = translationRepository.findById(translationId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        Client client = clientRepository.findById(1L)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        List<String> requestPdfs = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = "request/request/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            requestPdfs.add(amazonS3.getUrl(bucketName, fileName).toString());
        }

        Request request = Request.builder()
                .translation(translation)
                .client(client)
                .requestPdfs(requestPdfs)
                .build();
        requestRepository.save(request);
    }

    @Transactional
    public void acceptRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        request.accept();
    }

    @Transactional
    public void completeRequest(Long requestId, List<MultipartFile> files) throws IOException {
        List<String> responsePdfs = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = "request/response/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            responsePdfs.add(amazonS3.getUrl(bucketName, fileName).toString());
        }

        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));
        request.complete(responsePdfs);
    }
}
