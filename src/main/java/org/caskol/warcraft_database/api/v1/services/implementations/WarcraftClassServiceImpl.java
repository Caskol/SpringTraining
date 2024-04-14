//package org.caskol.warcraft_database.api.v1.services.implementations;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.caskol.warcraft_database.api.v1.dto.WarcraftClassDTO;
//import org.caskol.warcraft_database.api.v1.repositories.WarcraftClassRepository;
//import org.caskol.warcraft_database.api.v1.services.WarcraftClassService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Getter
//@Setter
//@Service
//@Transactional(readOnly = true)
//public class WarcraftClassServiceImpl implements WarcraftClassService {
//    private final WarcraftClassRepository warcraftClassRepository;
//    public Optional<WarcraftClassDTO> getById(int id)
//    {
//        return null;
//        //return warcraftClassRepository.findById(id);
//    }
//    @Transactional(readOnly = false)
//    public void save(WarcraftClassDTO warcraftClassDTO)
//    {
//        //warcraftClassRepository.save(warcraftClass);
//    }
//    @Transactional(readOnly = false)
//    public void delete(int id)
//    {
//        warcraftClassRepository.deleteById(id);
//    }
//    public List<WarcraftClassDTO> getAll()
//    {
//        return null;
//        //return warcraftClassRepository.findAll();
//    }
//
//
//}
