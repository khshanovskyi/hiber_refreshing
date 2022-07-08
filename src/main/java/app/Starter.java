package app;

import app.entity.Country;
import app.entity.Region;
import app.repository.EntityRepository;
import app.repository.EntityRepositoryImpl;
import app.service.CountryService;

import java.util.List;

public class Starter {
    public static void main1(String[] args) {
        EntityRepository<Country> countryRepository = new EntityRepositoryImpl<>(Country.class);
        addCountries(countryRepository);
//        EntityRepository<Region> regionRepository = new EntityRepositoryImpl<>(Region.class);
//        addRegions(regionRepository, countryRepository);

        System.out.println();
        System.out.println("==============COUNTRIES==============");
//        regionRepository.getAll().forEach(System.out::println);

        CountryService countryService = new CountryService(new EntityRepositoryImpl<>(Country.class));
        System.out.println(countryService.getByName("Ukraine"));
        System.out.println(countryService.getByAbbreviation("UK"));

    }

    private static void addCountries(EntityRepository<Country> countryRepository){
        countryRepository.saveAll(List.of(
                Country.builder().name("Ukraine").abbreviation("UA").build(),
                Country.builder().name("United Kingdom").abbreviation("UK").build(),
                Country.builder().name("United States of America").abbreviation("USA").build(),
                Country.builder().name("Italy").abbreviation("IT").build(),
                Country.builder().name("Poland").abbreviation("PL").build(),
                Country.builder().name("Estonia").abbreviation("EE").build(),
                Country.builder().name("Czech Republic").abbreviation("CZ").build(),
                Country.builder().name("Germany").abbreviation("DE").build()
        ));
    }

    private static void addRegions(EntityRepository<Region> regionRepository, EntityRepository<Country> countryRepository){
        regionRepository.saveAll(List.of(
                Region.builder().name("Kyiv").country(countryRepository.getById(1L)).build()
        ));
    }

}
