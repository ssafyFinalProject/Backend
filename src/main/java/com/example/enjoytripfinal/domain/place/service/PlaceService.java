package com.example.enjoytripfinal.domain.place.service;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import com.example.enjoytripfinal.domain.place.dto.request.PickPlaceRequest;
import com.example.enjoytripfinal.domain.place.dto.request.PlaceRequest;
import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.place.entity.Category;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.place.entity.PlaceMember;
import com.example.enjoytripfinal.domain.place.mapper.PlaceMapper;
import com.example.enjoytripfinal.domain.place.repository.PlaceMemberRepository;
import com.example.enjoytripfinal.domain.place.repository.PlaceRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    private final PlaceMemberRepository placeMemberRepository;
    private final MemberService memberService;
    private final PlaceMapper placeMapper;

    public PlaceService(PlaceRepository placeRepository,PlaceMemberRepository placeMemberRepository,MemberService memberService,PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMemberRepository = placeMemberRepository;
        this.memberService = memberService;
        this.placeMapper = placeMapper;
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> findPlaceListByCategory(String categoryName) {
        Category category = Category.toCategory(categoryName);
        return placeRepository.findAllByCategory(category).stream().map(placeMapper::entityToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> findPlaceListByName(String placeName) {
        return placeRepository.findAllByName(placeName).stream().map(placeMapper::entityToResponse).toList();
    }


    @Transactional(readOnly = true)
    public List<PlaceResponse> findPlaceListByRoadAddress(String roadAddress) {
        return placeRepository.findAllByRoadAddress(roadAddress).stream().map(placeMapper::entityToResponse).toList();
    }

    public List<PlaceResponse> findPlaceByDetail(PlaceRequest request) {
        return placeRepository.findAllByDetail(request.getName(),request.getCategory(),request.getRoadAddress()).stream().map(placeMapper::entityToResponse).toList();
    }

    public Place findPlaceById(UUID id) {
        return placeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public PlaceResponse pickMyPlace(PickPlaceRequest request) {
        Member curMember = memberService.getMemberByJwt();
        Place curPlace = findPlaceById(request.getPlaceId());

        if(alreadyExistPlace(curMember,curPlace))
            return placeMapper.entityToResponse(curPlace);

        curPlace.updateLike(false);
        PlaceMember placeMember = new PlaceMember(curPlace,curMember);
        placeMemberRepository.save(placeMember);

        return placeMapper.entityToResponse(curPlace);
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> getMyPickPlaces(Pageable pageable) {
        Member curMember = memberService.getMemberByJwt();
        return placeMemberRepository.findAllByMember(pageable,curMember.getId()).stream().map(x -> placeMapper.entityToResponse(x.getPlace())).toList();
    }

    @Transactional
    public void deleteMyPlace(PickPlaceRequest request) {
        Member curMember = memberService.getMemberByJwt();
        Place curPlace = findPlaceById(request.getPlaceId());
        PlaceMember placeMember = placeMemberRepository.findByMemberAndPlace(curMember,curPlace).orElseThrow(EntityNotFoundException::new);
        curPlace.updateLike(true);
        placeMemberRepository.deleteById(placeMember.getId());
    }

    private boolean alreadyExistPlace(Member member,Place place) {
        for(PlaceMember placeMember : member.getPlaces()) {
            if(placeMember.getPlace().getId().equals(place.getId())) {
                return true;
            }
        }
        return false;
    }
}


