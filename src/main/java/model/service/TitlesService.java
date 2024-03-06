package model.service;

import model.entity.Titles;
import model.entity.User;
import model.repository.TitlesRepository;
import model.repository.UserRepository;

import java.util.List;

public class TitlesService {

    private static TitlesService service = new TitlesService();

    private TitlesService() {
    }

    public static TitlesService getService() {
        return service;
    }

    public Titles save(Titles titles) throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
                return titlesRepository.save(titles);
            
        }
    }

    public Titles edit(Titles titles) throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
            return titlesRepository.edit(titles);
        }
    }

    public Titles remove(int id) throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
                return  titlesRepository.remove(id);
        }
    }

    public List<Titles> findAll() throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
            return titlesRepository.findAll();
        }
    }

    public Titles findById(int id) throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
            return titlesRepository.findById(id);
        }
    }

    public List<Titles> findByType(String type) throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
            return titlesRepository.findByType(type);
        }
    }

    public Titles findByName(String name) throws Exception {
        try (TitlesRepository titlesRepository = new TitlesRepository()) {
            return titlesRepository.findByName(name);
        }
    }

}
