package ru.skypro.homework.service;

import com.sun.istack.Interned;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO ad, MultipartFile image, Authentication authentication) throws IOException;

    ExtendedAdDTO getAds(Integer id);

    void removeAd(Integer id);

    AdDTO updateAds(Integer id, CreateOrUpdateAdDTO ad, Authentication authentication);

    AdsDTO getAdsMe(Authentication authentication);

    String updateImage(Integer id, MultipartFile image, Authentication authentication) throws IOException;

}
