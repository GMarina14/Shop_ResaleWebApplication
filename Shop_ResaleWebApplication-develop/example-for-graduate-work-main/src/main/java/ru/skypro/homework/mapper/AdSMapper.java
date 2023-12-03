package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;
import ru.skypro.homework.model.Image;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdSMapper {

    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInt")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    AdDTO entityToAdDTO(Ad ad);

    @Mapping(target = "author", qualifiedByName = "authorToInt")
    List<AdDTO> adDTOToList(List<Ad> adList);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToString")
    ExtendedAdDTO adToExtended(Ad ad);

    Ad createOrUpdateAdDTOToEntity(CreateOrUpdateAdDTO createOrUpdateAdDTO);

    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }

    @Named("imageToString")
    default String imageToString(Image image){

      return "/ads/image/"+ image.getId();

    }
}
