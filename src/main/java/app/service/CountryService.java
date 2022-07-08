package app.service;

import app.entity.Country;
import app.repository.EntityRepository;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Objects;

public class CountryService implements EntityService<Country>{
    private EntityRepository<Country> countryRepository;

    public CountryService(EntityRepository<Country> countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void saveAll(List<Country> countries) {
        countryRepository.saveAll(countries);
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.getById(id);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    @Override
    public Country deleteById(Long id) {
        return deleteById(id);
    }

    @Override
    public void update(Country countries) {
        countryRepository.update(countries);
    }

    public Country getByName(String name){
        List<Country> countries = countryRepository.getByCriteria(List.of(Restrictions.eq("name", name)));
        return Objects.nonNull(countries) && !countries.isEmpty() ? countries.get(0) : null;
    }

    public Country getByAbbreviation(String abbreviation){
        List<Country> countries = countryRepository.getByCriteria(List.of(Restrictions.eq("abbreviation", abbreviation)));
        return Objects.nonNull(countries) && !countries.isEmpty() ? countries.get(0) : null;
    }
}
