package controller;

import enums.TypeEnum;
import model.entity.Titles;
import model.entity.User;
import model.service.TitlesService;
import model.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j;


@Log4j

public class TitlesController {

    private static TitlesController controller = new TitlesController();

    private TitlesController() {
    }

    public static TitlesController getController() {
        return controller;
    }

    public Titles save(Integer id, String name, TypeEnum type) throws Exception {
        if (!name.isEmpty()) {
            Titles titles =
                    Titles
                            .builder()
                            .id(id)
                            .name(name)
                            .type(type)
                            .build();
            TitlesService.getService().save(titles);
            log.info("save");
            return titles;
        } else {
            log.error("can not save");
            throw new Exception("Invalid Data");
        }
    }

    public Titles edit(Integer id, String name, TypeEnum type) throws Exception {
        if (!name.isEmpty()) {
            Titles titles =
                    Titles
                            .builder()
                            .id(id)
                            .name(name)
                            .type(type)
                            .build();
            TitlesService.getService().edit(titles);
            log.info("edit");

            return titles;
        } else {
            throw new Exception("Invalid Data");
        }
    }

    public Titles remove(Integer id) {
        try {
            Titles titles = TitlesService.getService().findById(id);
            if (titles != null) {
                TitlesService.getService().remove(id);
                log.info("remove");
                return titles;
            } else {
                System.out.println("titles not find");
                return null;
            }
        } catch (Exception e) {
            log.error("Error to remove");
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Titles> findAll() {
        try {
            log.info("find all");
            return TitlesService.getService().findAll();

        } catch (Exception e) {
            log.error("Error to find");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Titles findById(Integer id) {
        try {
            log.info("find by id");
            return TitlesService.getService().findById(id);

        } catch (Exception e) {
            log.error("Error to find");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

    public List<Titles> findByType(String type) {
        try {
            log.info("find all by type");
            return TitlesService.getService().findByType(type);

        } catch (Exception e) {
            log.error("Error to find by type");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
}
