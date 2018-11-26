package ca.mydemo.tcoffice.service.impl;

import ca.mydemo.tcoffice.dao.mapper.BizMapper;
import ca.mydemo.tcoffice.domain.Client;
import ca.mydemo.tcoffice.domain.Opportunity;
import ca.mydemo.tcoffice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    BizMapper bizMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Client> loadClients(Date from, Date to) {
        return bizMapper.loadAllBiz();
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClient(int bizId) {
        return bizMapper.getClient(bizId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Opportunity> loadOpportunity(int bizId) {
        return bizMapper.loadOpportunity(bizId);
    }
}
