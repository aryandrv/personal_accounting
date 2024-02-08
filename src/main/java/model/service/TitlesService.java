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
            Titles titles= titlesRepository.findById(id);
            if (titles != null){
                titlesRepository.remove(id);
                return titles;
            }
            else {
                return null;
            }
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

}
