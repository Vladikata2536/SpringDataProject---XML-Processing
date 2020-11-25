package com.example.xmlexercisepartone.services;

import com.example.xmlexercisepartone.dtos.export.*;
import com.example.xmlexercisepartone.dtos.importDTO.UserImportDTO;
import com.example.xmlexercisepartone.dtos.importDTO.UserImportRootDTO;
import com.example.xmlexercisepartone.entities.User;
import com.example.xmlexercisepartone.repositories.UserRepo;
import com.example.xmlexercisepartone.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static String USERS_PATH = "src/main/resources/09. XML-Processing-Exercises/users.xml";
    private static String USERS_EXPORT_PATH = "src/main/resources/09. XML-Processing-Exercises/exported/second";
    private static String USERS_EXPORT_LAST_PATH = "src/main/resources/09. XML-Processing-Exercises/exported/fourth";

    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final XmlParser xmlParser;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedUsers() throws JAXBException {
        UserImportRootDTO rootDTO = this.xmlParser.parseXml(UserImportRootDTO.class, USERS_PATH);

        for (UserImportDTO dtoUser : rootDTO.getUsers()) {
            User user = this.modelMapper.map(dtoUser, User.class);
            this.userRepo.saveAndFlush(user);
        }

    }

    @Override
    @Transactional
    public void seedFriends() throws Exception {
        List<User> allUsers = this.userRepo.findAll();
        if(allUsers.size() > 1){
            for (User user : allUsers) {
                user.setFriends(getRandomFriends(user.getId()));
                this.userRepo.save(user);
            }

        }else {
            System.out.println("Not enough users in the repo");
        }
    }

    @Override
    public void getAllUsersBySoldProducts() throws IOException, JAXBException {
        Set<User> users = this.userRepo.getAllBySoldProducts();
        UserExportRootDTO rootDTO = new UserExportRootDTO();
        List<UserExportDTO> dtos = new ArrayList<>();

        for (User user : users) {
            UserExportDTO dto = this.modelMapper.map(user, UserExportDTO.class);
            List<ProductExportUserDTO> sold = dto.getSold().getSold();
            sold = sold.stream().filter(p -> p.getBuyer() != null).collect(Collectors.toList());
            dto.getSold().setSold(sold);
            if(sold.size() > 0) {
                dtos.add(dto);
            }
        }
        rootDTO.setSold(dtos);
        this.xmlParser.exportXml(rootDTO,UserExportRootDTO.class,USERS_EXPORT_PATH);

    }

    @Override
    public void getAllUsersLast() throws IOException, JAXBException {
        Set<User> users = this.userRepo.getAllBySoldProductsLast();
        UserExportLastRootDTO rootDTO = new UserExportLastRootDTO();
        List<UserExportLastDTO> dtos = new ArrayList<>();

        for (User user : users) {
            UserExportLastDTO dto = this.modelMapper.map(user, UserExportLastDTO.class);
            dto.getSold().setCount(user.getSold().size());
            dtos.add(dto);
        }
        rootDTO.setUsers(dtos);

        this.xmlParser.exportXml(rootDTO,UserExportLastRootDTO.class,USERS_EXPORT_LAST_PATH);

    }

    private Set<User> getRandomFriends(Long id) throws Exception {
        Set<User> users = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            User user = this.getRandomUser();
            if(!user.getId().equals(id)) {
                users.add(user);
            }
        }
        return users;
    }
    private User getRandomUser() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.userRepo.count()) + 1;
        Optional<User> part = this.userRepo.findById(index);
        if (part.isPresent()) {
            return part.get();
        } else {
            throw new Exception("Invalid part id");
        }
    }


}
