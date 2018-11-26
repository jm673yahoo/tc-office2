package ca.mydemo.tcoffice.dao.mapper;


import ca.mydemo.tcoffice.domain.Client;
import ca.mydemo.tcoffice.domain.Opportunity;
import org.springframework.stereotype.Repository;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;

@Repository
public interface BizMapper {
    List<Client> loadAllBiz();

    Client getClient(int bizId);

    List<Opportunity> loadOpportunity(int bizId);
}
